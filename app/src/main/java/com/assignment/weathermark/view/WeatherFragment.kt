package com.assignment.weathermark.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.weathermark.databinding.FragmentWeatherBinding
import com.assignment.weathermark.repository.WeatherRepositoryImp
import com.assignment.weathermark.viewmodel.WeatherViewModel

class WeatherFragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding get() = _binding!!

    lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter

    private val viewModel : WeatherViewModel by lazy {
        object  : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepositoryImp()) as T
            }
        }.create(WeatherViewModel::class.java)
    }


}