package com.assignment.weathermark.model

data class RequestApi (
    val dt: Int,
    val dt_txt: String,
    val pop: Int,
    val visibility: Int,
    val weather: List<Weather>,

)