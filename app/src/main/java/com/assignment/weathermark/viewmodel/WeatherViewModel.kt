package com.assignment.weathermark.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.weathermark.model.Weather
import com.assignment.weathermark.model.ResponseWeather
import com.assignment.weathermark.repository.WeatherRepositoryImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// androidx == Jetpack Libraries
class WeatherViewModel(
    private val repositoryImpl: WeatherRepositoryImp
) : ViewModel() {


    init {
        getWeather(null)
    }


    private val _weather = MutableLiveData<List<Weather>>()

    val weather: LiveData<List<Weather>> get() = _weather

    fun getWeather(q: String? ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryImpl.getWeather(q = q)
            _weather.postValue(response.weather)
        }
    }
}