package com.jana.weatherapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jana.weatherapp.databinding.ActivityMainBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var weatherAdapter: WeatherAdapter?=null
    private var binding: ActivityMainBinding?=null
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
             weatherAdapter=WeatherAdapter(object : WeatherAdapter.ClickListener{
                 override fun onItemClickListener(position: Int) {
                     val intent=Intent(this@MainActivity,WeatherDetailActivity::class.java)
                     intent.putExtra("POSITION",position)
                     startActivity(intent)
                 }
             })
            adapter=weatherAdapter
        }

        val dao = WeatherDatabase.invoke(application).getWeatherDao()
        val repository = WeatherRepository(dao)
        val factory = WeatherViewModelFactory(repository)
        weatherViewModel = ViewModelProvider(this@MainActivity,factory)[WeatherViewModel::class.java]

        dao.getAllData().observe(this@MainActivity) {
            if (date() || it.isNullOrEmpty() ){
                repository.getServicesApiCall()
            }
            weatherAdapter?.refreshList(it as ArrayList<WeatherDataModel>)
        }
    }

    fun date(): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREF_SAVE", Context.MODE_PRIVATE)
        val calendar = Calendar.getInstance()
        val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
        val today = calendar.time
        val todayAsString = dateFormat.format(today)

        return if (sharedPreferences.getString("date_key","") == todayAsString)
            false
        else {
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("date_key",todayAsString)
            editor.apply()
            editor.commit()
            true
        }

    }


}