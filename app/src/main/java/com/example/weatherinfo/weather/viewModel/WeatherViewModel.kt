package com.example.weatherinfo.weather.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherinfo.net.HttpUtils
import com.example.weatherinfo.weather.model.Http
import com.example.weatherinfo.weather.model.WeatherInfoBeen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _cityId = MutableLiveData<String>().also { it.value = "101211101" }
    private val _weatherInfo = MutableLiveData<WeatherInfoBeen>()
        .also { it.value = WeatherInfoBeen("","","","","","","","") }

    val weatherInfo = _weatherInfo
    val cityId = _cityId

    fun setCityId(cityId:String){
        _cityId.value = cityId
    }

    fun fetchData(){
        CoroutineScope(Dispatchers.Main).launch {
            val res = _cityId.value?.let { Http().fetchData(it) }
            Log.e(TAG, "fetchData!!!!: $res" )

            _weatherInfo.value = res ?:
            WeatherInfoBeen("null","错误的城市代码","null","null","null","null","null","null")

        }
    }


}