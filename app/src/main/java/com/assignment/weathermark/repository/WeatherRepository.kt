package com.assignment.weathermark.repository
import android.util.Log
import com.assignment.weathermark.api.ApiService
import com.assignment.weathermark.model.RequestApi
import com.assignment.weathermark.model.ResponseWeather

// makes calls to the data source
// contract
interface WeatherRepository {
    suspend fun getWeather(q: String?): ResponseWeather
}
class  WeatherRepositoryImp(private val service: ApiService = ApiService.getApiService()):WeatherRepository{

    override suspend fun getWeather(q: String?): ResponseWeather {
        val response = service.getWeather()
        println("**********************************************************" + response.body())
        return if (response.isSuccessful){
            response.body()!!
        }else{
            Log.d("asd","network is shit2")
            ResponseWeather(emptyList())
        }
    }

}