package com.app.weather.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weather.BuildConfig
import com.app.weather.R
import com.app.weather.adapter.WeatherAdapter
import com.app.weather.base.BaseActivity
import com.app.weather.extension.hide
import com.app.weather.extension.show
import com.app.weather.model.LatLng
import com.app.weather.model.WeatherModel
import com.app.weather.utils.CommonUtils
import com.app.weather.utils.Vars
import com.app.weather.viewmodel.HomeViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    lateinit var homeViewModel: HomeViewModel
    lateinit var latLng: LatLng
    var city = ""
    lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // to restrict crash on 8.1.0 custom os devices added below lines
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            window.decorView.importantForAutofill =
                View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }

        attachViewModel()
        initView()
        prepareClickListener()
    }

    private fun initView() {
        toolbar.title = getString(R.string.home)
        setSupportActionBar(toolbar)
        clResult.hide()
        tvHint.show()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        rvWeather.layoutManager = LinearLayoutManager(this@HomeActivity)
    }

    private fun prepareClickListener() {
        btnSubmit.setOnClickListener {
            city = etCity.text.toString().trim()
            if (isValid())
            //showing data from api when internet is available or showing from local db if data is available
                if (CommonUtils.isInternetAvailable(this@HomeActivity)) {
                    accessBySearch()
                }
        }
    }

    private fun isValid(): Boolean {
        var isValid = true
        if (city.isEmpty()) {
            etCity.error = getString(R.string.err_enter_city_name)
            isValid = false
        }
        return isValid
    }

    private fun accessByLocation() {
        hideKeyboard(this@HomeActivity)
        //showing progress
        llProgressBar.show()
        homeViewModel.accessWeather(
            latLng.lat.toString(), latLng.lng.toString(),Vars.units, BuildConfig.APP_KEY
        ).observe(this, Observer {
            //dismissing progress
            llProgressBar.hide()
            if (it != null) {
                prepareView(it)
            }
        })
    }

    private fun accessBySearch() {
        hideKeyboard(this@HomeActivity)
        //showing progress
        llProgressBar.show()
        homeViewModel.accessCityWeather(
            city,Vars.units, BuildConfig.APP_KEY
        ).observe(this, Observer {
            //dismissing progress
            llProgressBar.hide()
            if (it != null) {
                prepareView(it)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun prepareView(it: WeatherModel) {
        try {
            clResult.show()
            tvHint.hide()
            tv_city_name.text = it.city.name
            tvWeather.text = it.list[0].weather[0].description
            tvTemp.text =
                it.list[0].main.temp.toString() + getString(R.string.degree_celsius)
            tv_hum.text = "${"H :"} ${it.list[0].main.humidity} ${getString(R.string.degree)}"
            tv_Low.text = "${"L :"} ${it.list[0].main.temp_min} ${getString(R.string.degree_celsius)}"
            weatherAdapter = WeatherAdapter(this@HomeActivity,it)
            rvWeather.adapter = weatherAdapter
        } catch (e: Exception) {
            e.printStackTrace()
            clResult.hide()
            tvHint.text = getString(R.string.no_data_available)
            tvHint.show()
        }
    }

    //attach view model here
    private fun attachViewModel() {
        homeViewModel = ViewModelProvider(this@HomeActivity).get(HomeViewModel::class.java)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
     private fun getLastLocation() {
        if (CommonUtils.isInternetAvailable(this@HomeActivity)) {
            if (CommonUtils.checkPermissions(applicationContext)) {
                if (CommonUtils.isLocationEnabled(applicationContext)) {
                    mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                        var location: Location? = task.result
                        if (location == null) {
                            requestNewLocationData()
                        } else {
                            latLng = LatLng(
                                location.latitude,
                                location.longitude
                            )
                            accessByLocation()
                        }
                    }
                } else {
                    Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }
            } else {
                requestPermissions()
            }
        }else{
            clResult.hide()
            tvHint.text = getString(R.string.no_internet_connection)
            tvHint.show()
        }
    }

    @SuppressLint("MissingPermission")
    fun requestNewLocationData() {
        var mLocationRequest = LocationRequest.create()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()!!
        )
    }

    val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            latLng = LatLng(mLastLocation.latitude,
                mLastLocation.longitude)
            accessByLocation()
        }
    }


}