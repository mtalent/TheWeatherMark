package com.assignment.weathermark.repository
import android.util.Log
import com.assignment.weathermark.api.ApiService
import com.assignment.weathermark.model.RequestApi
import com.assignment.weathermark.model.ResponseWeather


interface WeatherRepository {
    suspend fun getWeather(q: String?): ResponseWeather
}
class  WeatherRepositoryImp(private val service: ApiService = ApiService.getApiService()):WeatherRepository{

    override suspend fun getWeather(q: String?): ResponseWeather {
        val response = service.getWeather()

        return if (response.isSuccessful){
            response.body()!!
        }else{

            ResponseWeather(emptyList())
        }
    }

}