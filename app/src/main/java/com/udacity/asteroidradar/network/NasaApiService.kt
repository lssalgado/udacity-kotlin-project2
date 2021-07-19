package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build()
//private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("start_date") beginDate: String, @Query("end_date") endDate: String, @Query("api_key") key: String): String

//    @GET("planetary/apod")
//    suspend fun getImageOfTheDay(@Query("api_key") key: String): ImageOfTheDay
}

object NasaApi {
    val retrofitService: NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}