package com.example.weatherinfo.net

import android.content.ContentValues.TAG
import android.util.Log
import com.example.weatherinfo.weather.model.HttpBeen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class HttpUtils {

    val client = OkHttpClient()

    suspend fun get(url:String) : HttpBeen = withContext(Dispatchers.IO){
        try {
            val request = Request.Builder()
                    .url(url)
                    .build()
            Log.e(TAG, "get: !!!", )
            return@withContext HttpBeen(client.newCall(request).await(),"")
        }catch (e:IOException){
            return@withContext HttpBeen(null,e.toString())
        }

    }



    suspend fun Call.await():Response{
        return suspendCoroutine {
            enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    it.resumeWithException(e)
                }
                override fun onResponse(call: Call, response: Response) {
                   it.resume(response)
                }

            })
        }
    }


}