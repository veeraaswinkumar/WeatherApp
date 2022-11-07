package com.app.weather.activity

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.app.weather.R
import com.app.weather.base.BaseActivity
import com.app.weather.extension.launchActivity
import com.app.weather.viewmodel.HomeViewModel
import com.app.weather.viewmodel.SplashViewModel

class SplashActivity : BaseActivity() {
    lateinit var splashViewModel : SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        attachViewModel()
        Handler().postDelayed({
            launchActivity<HomeActivity> {  }
            finish()
        },2000)
    }

    private fun attachViewModel() {
        splashViewModel = ViewModelProvider(this@SplashActivity).get(SplashViewModel::class.java)
    }
}