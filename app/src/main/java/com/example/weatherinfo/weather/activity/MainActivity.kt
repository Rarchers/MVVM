package com.example.weatherinfo.weather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherinfo.R
import com.example.weatherinfo.weather.viewModel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    lateinit var fetch :Button
    lateinit var city:TextView
    lateinit var cityid:TextView
    lateinit var temp1:TextView
    lateinit var temp2:TextView
    lateinit var weather:TextView
    lateinit var inputcity:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        fetch = findViewById(R.id.button)
        city = findViewById(R.id.city)
        cityid = findViewById(R.id.cityid)
        temp1 = findViewById(R.id.temp1)
        temp2 = findViewById(R.id.temp2)
        weather = findViewById(R.id.weather)
        inputcity = findViewById(R.id.inputcity)
        inputcity.setText("101211101")

        fetch.setOnClickListener{
            val inputstream = inputcity.text.toString()
            viewModel.setCityId(inputstream)
            viewModel.fetchData()
        }

        viewModel.weatherInfo.observe(this, Observer {
            city.text = ":  ${it.city}"
            cityid.text = ":  ${it.cityid}"
            temp1.text = ":  ${it.temp1}"
            temp2.text = ":  ${it.temp2}"
            weather.text = ":  ${it.weather}"
        })
    }
}