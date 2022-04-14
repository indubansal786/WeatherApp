package com.jana.weatherapp

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "weather_table")
data class WeatherDataModel (@PrimaryKey(autoGenerate = true)
                              val id: Int=0,
                             val timepoint: Int?,
                             val temp2m: Int?,
                             val cloudcover: Int?,
                             val seeing: Int?,
                             val transparency: Int?,
                             val lifted_index: Int?,
                             val rh2m: Int?,
                             val prec_type: String?,
                             @Embedded
                              val wind10m: WeatherDr?,
                              )

data class WeatherDr( val speed: Int?,
                      val direction: String?)

data class WeatherModel(val dataseries:List<WeatherDataModel>)

