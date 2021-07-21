package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build()

interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("start_date") beginDate: String, @Query("end_date") endDate: String, @Query("api_key") key: String): String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") key: String): String
}

object NasaApi {
    val retrofitService: NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}