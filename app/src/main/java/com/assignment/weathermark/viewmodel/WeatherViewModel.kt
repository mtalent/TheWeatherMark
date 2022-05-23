package com.assignment.weathermark.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.weathermark.model.Weather
import com.assignment.weathermark.model.ResponseWeather
import com.assignment.weathermark.repository.WeatherRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// androidx == Jetpack Libraries
class WeatherViewModel(
    private val repositoryImpl: WeatherRepositoryImpl
) : ViewModel() {

    // this block is called when the class is first created
    init {
        getWeather(null, null, null)
    }

    // LiveData -> is a wrapper class for observable data
    // is also lifecycle aware
    // private MutableLiveData is updated by the ViewModel
    private val _weather = MutableLiveData<List<Weather>>()
    // public LiveData will be observed by the View Layer
    val weather: LiveData<List<Weather>> get() = _weather

    fun getWeather(q: String?, appid: String?, unit: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryImpl.getWeather(q = q, appid = appid, unit = unit)
            _weather.postValue(response.weather)
        }
    }
}