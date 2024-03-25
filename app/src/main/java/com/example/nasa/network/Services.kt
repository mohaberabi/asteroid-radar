package com.example.nasa.network

import android.util.Log
import com.example.nasa.model.Asteroid
import com.example.nasa.model.ImageOfDay
import com.example.nasa.util.Constants
import okhttp3.ResponseBody
import org.json.JSONObject
import parseAsteroidsJsonResult
import parseImageOfDayResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServices {


    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidsResponse(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("start_date") startDate: String = Constants.getCurrentDateFormatted(),
        @Query("end_date") endDate: String = Constants.getAfterSevenDaysFormatted()
    ): Response<ResponseBody>

    @GET("planetary/apod")
    suspend fun getImageOfTheDay(
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ): Response<ResponseBody>


}


object AsteroidsServices {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .build()
    private val asteroidRetrofit: NetworkServices = retrofit.create(NetworkServices::class.java)

    suspend fun fetchAsteroids(
        startDate: String = Constants.getCurrentDateFormatted(),
        endDate: String = Constants.getAfterSevenDaysFormatted()
    ): List<Asteroid>? {

        try {
            val response = asteroidRetrofit.getAsteroidsResponse(
                startDate = startDate,
                endDate = endDate
            )


            val jsonString = response.body()?.string()
            val jsonObject = jsonString?.let { JSONObject(it) }
            Log.i("asteroidResponse", " {${jsonString.toString()}")
            val res = jsonObject?.let { parseAsteroidsJsonResult(it) }
            Log.i("responseLength", " {${res?.size.toString()}")
            return res
        } catch (e: Exception) {


            Log.e("netowrkServiceError", " {${e.toString()}")
            return null
        }

    }

    suspend fun fetchImageOfTheDay(): ImageOfDay {

        try {
            val response = asteroidRetrofit.getImageOfTheDay()


            val jsonString = response.body()?.string()
            Log.i("imgOfDayResponse", " {${jsonString.toString()}")
            return jsonString?.let {
                parseImageOfDayResponse(jsonString)
            } ?: ImageOfDay.empty
        } catch (e: Exception) {
            Log.e("imgOfDayResponseError", " {${e.toString()}")
            return ImageOfDay.empty

        }

    }


}