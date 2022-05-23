package com.assignment.weathermark.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.assignment.weathermark.R
import com.assignment.weathermark.databinding.WeatherListItemBinding
import com.assignment.weathermark.model.Weather

// Unit -> void
// Unit just returns the function call
// Nothing != Unit
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
                // this ^ is calling this \/ from the UsersFragment
//                    fun openDetails(randomUser: RandomUser) {
//                        parentFragmentManager.beginTransaction()
//                            .replace(R.id.fragment_container, DetailsFragment.newInstance(randomUser))
//                            .addToBackStack(null)
//                            .commit()
//                    }
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