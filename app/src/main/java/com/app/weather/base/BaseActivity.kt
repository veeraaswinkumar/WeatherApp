package com.app.weather.base

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.app.weather.MyApplication
import com.app.weather.model.LatLng
import com.google.android.gms.location.*
import java.util.*


abstract class BaseActivity : AppCompatActivity() {

    lateinit var myApplication: MyApplication

    var LOCATION_PERMISSION_REQUEST_CODE = 101

    lateinit var mFusedLocationClient : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myApplication = applicationContext as MyApplication
        myApplication.appComponent.inject(this)
    }

    fun getAddressFromLatLng(latitude: Double, longitude: Double): String {
        return try {
            val geoCoder = Geocoder(this, Locale.getDefault())
            val addresses = geoCoder.getFromLocation(latitude, longitude, 1)
            addresses.get(0).getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun getLatLngFromCity(city : String): LatLng {
        var latLng = LatLng(0.0,0.0)
        return try {
            val geoCoder = Geocoder(this, Locale.getDefault())
            val addresses = geoCoder.getFromLocationName(city, 1)
            latLng.lat = addresses[0].latitude
            latLng.lng = addresses[0].longitude
            latLng
        } catch (e: Exception) {
            e.printStackTrace()
            latLng
        }
    }

    fun showAlert(title: String) {
        AlertDialog.Builder(this@BaseActivity).setMessage(title).setPositiveButton("Ok",object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.dismiss()
            }
        }).show()
    }

    open fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

     fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }
}