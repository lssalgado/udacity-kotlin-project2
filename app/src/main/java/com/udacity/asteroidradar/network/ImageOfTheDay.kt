package com.udacity.asteroidradar.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageOfTheDay(val copyright: String,
                         val date: String,
                         val explanation: String,
                         val hdurl: String,
                         val media_type: String,
                         val service_version: String,
                         val title: String,
                         val url: String) : Parcelable {
    val isImage = media_type == "image"

    object Keys{
        val copyright = "copyright"
        val date = "date"
        val explanation = "explanation"
        val hdurl = "hdurl"
        val media_type = "media_type"
        val service_version = "service_version"
        val title = "title"
        val url = "url"
    }
}