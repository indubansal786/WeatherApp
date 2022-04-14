package com.jana.weatherapp

import androidx.lifecycle.ViewModel

 class WeatherViewModel(
    var repository: WeatherRepository
): ViewModel() {

    suspend fun clearDb() = repository.clearNote()

    fun getAllData() = repository.getAllNotes()
}
