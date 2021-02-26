package com.example.weatherinfo.weather.model

data class WeatherInfoBeen(
        var city:String,
        var cityid:String,
        var temp1:String,
        var temp2:String,
        var weather:String,
        var img1:String,
        var img2:String,
        var ptime:String
){
    override fun toString(): String {
        return "WeatherInfo[city $city cityid $cityid temp1 $temp1 temp2 $temp2 weather $weather]"
    }
}
