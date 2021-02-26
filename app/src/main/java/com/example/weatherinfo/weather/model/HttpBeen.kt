package com.example.weatherinfo.weather.model

import okhttp3.Response
import java.lang.Exception

data class HttpBeen(val response: Response?,val exception: String)
