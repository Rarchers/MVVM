package com.example.weatherinfo.weather.model

import android.content.ContentValues.TAG
import android.util.Log
import com.example.weatherinfo.net.HttpUtils
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception


class Http {
    val httpUtils = HttpUtils()
   /* suspend fun fetchData(cityId: String): WeatherInfoBeen? {
        Log.e(TAG, "fetchData: $cityId", )
        val res = httpUtils.get("http://www.weather.com.cn/data/cityinfo/${cityId}.html")
        return if (res.exception != "") {
            null
        } else { var data = res.response?.body()?.string()
            data = data?.substring(15,data.length-1)
            val weatherInfoBeen = Gson().fromJson(data,WeatherInfoBeen::class.java)
            weatherInfoBeen
        }
    }*/

     suspend fun fetchData(cityId: String): WeatherInfoBeen?  = withContext(Dispatchers.IO){
        Log.e(TAG, "fetchData: $cityId", )
        val res = httpUtils.get("http://www.weather.com.cn/data/cityinfo/${cityId}.html")
         return@withContext if (res.exception != "") {
             null
         } else { var data = res.response?.body()?.string()
             Log.e(TAG, "fetchData: $data", )
             /*data = data?.substring(15,data.length-1)
             Log.e(TAG, "fetchData: $data", )*/
             var weatherInfoBeen :WeatherBeen? = null
             try {
                 weatherInfoBeen = Gson().fromJson(data,WeatherBeen::class.java)
             }catch (e:Exception){
                 e.printStackTrace()
             }

             weatherInfoBeen?.weatherinfo
         }
    }

}
