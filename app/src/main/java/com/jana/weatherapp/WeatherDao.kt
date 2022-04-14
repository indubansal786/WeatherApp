package com.jana.weatherapp

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WeatherDao {

    @Insert
    fun insertList(note: List<WeatherDataModel>?)


    @Query("SELECT * FROM weather_table")
    fun getAllData(): LiveData<List<WeatherDataModel>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    @Query("DELETE FROM weather_table")
    suspend fun clearDb()

    @Query("SELECT * FROM weather_table WHERE id = :id")
    fun selectSingleItem(id: Int): WeatherDataModel


}