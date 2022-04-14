package com.jana.weatherapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [WeatherDataModel::class],
    version = 1,
    exportSchema = false
)

public abstract class WeatherDatabase: RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao

    companion object {
        private const val DB_NAME = "weather_table.db"
        @Volatile private var instance: WeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            DB_NAME
        ).build()
    }
}