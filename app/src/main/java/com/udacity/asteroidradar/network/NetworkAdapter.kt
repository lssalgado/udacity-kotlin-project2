package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.PictureOfDay

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val adapter = moshi.adapter<PictureOfDay>(PictureOfDay::class.java)

fun pictureOfDayFromString(string: String): PictureOfDay {
    return adapter.fromJson(string)!!
}