package com.jana.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository (  private val weatherDao: WeatherDao){

    suspend fun clearNote() = weatherDao.clearDb()

    fun getAllNotes():LiveData<List<WeatherDataModel>> = weatherDao.getAllData()

    fun getServicesApiCall() {

        var retrofitService=RetrofitService.getInstance()
        val call: Call<WeatherModel> = retrofitService.getWeatherList()
        call.enqueue(object : Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    GlobalScope.launch {
                        weatherDao.insertList(response.body()?.dataseries)                    }
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("main", "onFailure: " + t.message)
                Log.d("main", "onFailure: " + call)
                Log.d("main", "onFailure: " + t)
            }
        })

    }





}