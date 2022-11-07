package com.app.weather

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.app.weather.dagger.AppComponent
import com.app.weather.dagger.AppModule
import com.app.weather.dagger.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }
}