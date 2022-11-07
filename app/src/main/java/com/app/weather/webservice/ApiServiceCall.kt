package com.app.weather.webservice

import com.app.weather.model.WeatherModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response


object ApiServiceCall {

    fun accessWeather(
        lat: String,
        lon: String,
        units: String,
        appid: String,
        webApiService: ApiServiceInterface
    ): Observable<Response<WeatherModel>> {
        return webApiService.accessWeather(lat, lon,units, appid)
    }
    fun accessCityWeather(
        city: String,
        units: String,
        appid: String,
        webApiService: ApiServiceInterface
    ): Observable<Response<WeatherModel>> {
        return webApiService.accessCityWeather(city,units, appid)
    }


}