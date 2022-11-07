package com.app.weather.webservice

import com.app.weather.model.WeatherModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.*


interface ApiServiceInterface {


    @GET("data/2.5/forecast")
    fun accessWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Observable<Response<WeatherModel>>

    @GET("data/2.5/forecast")
    fun accessCityWeather(
        @Query("q") lat: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Observable<Response<WeatherModel>>


}