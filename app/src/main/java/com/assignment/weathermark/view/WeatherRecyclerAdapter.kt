package com.assignment.weathermark.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.assignment.weathermark.R
import com.assignment.weathermark.databinding.WeatherListItemBinding
import com.assignment.weathermark.model.Weather


class WeatherRecyclerAdapter(
    private val weatherList: MutableList<Weather> = mutableListOf(),
    private val openDetails: (Weather) -> Unit
): RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {

    fun setUserList(newList: List<Weather>) {
        weatherList.clear()
        weatherList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(
        private val binding: WeatherListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Weather) {
            val imageUrl = "http://openweathermap.org/img/wn/" + weather.icon + "@2x.png"
            Glide.with(binding.ivWeatherImage)
                .load(imageUrl)
                .into(binding.ivWeatherImage)

            binding.tvWeather.text = weather.description
            binding.tvWeatherType.text = weather.main


            binding.root.setOnClickListener {
                openDetails(weather)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(
            WeatherListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount() = weatherList.size
}