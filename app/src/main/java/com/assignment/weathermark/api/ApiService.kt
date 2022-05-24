package com.assignment.weathermark.api

import com.assignment.weathermark.model.RequestApi
import com.assignment.weathermark.model.ResponseWeather
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast/")
    suspend fun getWeather(
        @Query("q") q: String? = null,
        @Query("appid") appid: String? = null,
        @Query("unit") unit: String? = null
    ): Response<ResponseWeather>

    companion object {
        private var instance: ApiService? = null
        fun getApiService(): ApiService {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return instance!!
        }
    }

}