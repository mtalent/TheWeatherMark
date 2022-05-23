package com.assignment.weathermark.repository
import android.util.Log
import com.assignment.marktalentweather.model.ResponseWeather
import com.assignment.weathermark.api.ApiService
import com.assignment.weathermark.model.Clouds
import com.assignment.weathermark.model.ResponseWeather

// makes calls to the data source
// contract
interface WeatherRepository {
    suspend fun getWeather(q: String?, appid: String?, unit: String?): ResponseWeather
}

class WeatherRepositoryImpl(
    private val service: ApiService = ApiService.getApiService()
): WeatherRepository {
    override suspend fun getWeather(q:String?, appid: String?, unit: String?): ResponseWeather {
        val response = service.getWeather(q = q, appid = appid, unit = unit)

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            ResponseWeather(emptyList())
        }
    }
}