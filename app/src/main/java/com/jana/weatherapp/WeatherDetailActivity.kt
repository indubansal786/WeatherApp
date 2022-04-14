package com.jana.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jana.weatherapp.databinding.WeatherDetailBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherDetailActivity : AppCompatActivity() {

    private var binding: WeatherDetailBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeatherDetailBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        GlobalScope.launch {
            val dao = WeatherDatabase.invoke(application).getWeatherDao()
            val model=dao.selectSingleItem(1+intent.getIntExtra("POSITION",0))
            binding?.apply {
                text.text=model.timepoint.toString()
                text1.text=model.temp2m.toString()
                text2.text=model.cloudcover.toString()
                text3.text=model.seeing.toString()
                text4.text=model.transparency.toString()
                text5.text=model.lifted_index.toString()
                text6.text=model.rh2m.toString()
                text7.text=model.prec_type.toString()
                text8.text=model.wind10m?.speed.toString()
                text9.text=model.wind10m?.direction.toString()

                imgBack.setOnClickListener {
                    finish()
                }
            }
        }
    }

}