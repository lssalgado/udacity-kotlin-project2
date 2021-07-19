package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.network.ImageOfTheDay
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/*
Using the parse function suggested by Diraj H
Reference: https://knowledge.udacity.com/questions/470665
*/
fun parseAsteroidsJsonResult(jsonObject: JSONObject): List<Asteroid> {
    val asteroidList = mutableListOf<Asteroid>()
    val nearEarthObjectsJson = jsonObject.getJSONObject("near_earth_objects")
    val dateList = nearEarthObjectsJson.keys()
    val dateListSorted = dateList.asSequence().sorted()
    dateListSorted.forEach {
        val key: String = it
        val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(key)
        for (i in 0 until dateAsteroidJsonArray.length()) {
            val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
            val id = asteroidJson.getLong("id")
            val codename = asteroidJson.getString("name")
            val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
            val estimatedDiameter = asteroidJson.getJSONObject("estimated_diameter")
                    .getJSONObject("kilometers").getDouble("estimated_diameter_max")
            val closeApproachData = asteroidJson
                    .getJSONArray("close_approach_data").getJSONObject(0)
            val relativeVelocity = closeApproachData.getJSONObject("relative_velocity")
                    .getDouble("kilometers_per_second")
            val distanceFromEarth = closeApproachData.getJSONObject("miss_distance")
                    .getDouble("astronomical")
            val isPotentiallyHazardous = asteroidJson
                    .getBoolean("is_potentially_hazardous_asteroid")
            val asteroid = Asteroid(
                    id,
                    codename,
                    key,
                    absoluteMagnitude,
                    estimatedDiameter,
                    relativeVelocity,
                    distanceFromEarth,
                    isPotentiallyHazardous
            )
            asteroidList.add(asteroid)
        }
    }
    return asteroidList
}

private fun getNextSevenDaysFormattedDates(): ArrayList<String> {
    val formattedDateList = ArrayList<String>()

    val calendar = Calendar.getInstance()
    for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        formattedDateList.add(dateFormat.format(currentTime))
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return formattedDateList
}

fun parseImageOfTheDay(jsonObject: JSONObject): ImageOfTheDay {
    val copyright = jsonObject.getString(ImageOfTheDay.Keys.copyright)
    val date = jsonObject.getString(ImageOfTheDay.Keys.date)
    val explanation = jsonObject.getString(ImageOfTheDay.Keys.explanation)
    val hdurl = jsonObject.getString(ImageOfTheDay.Keys.hdurl)
    val media_type = jsonObject.getString(ImageOfTheDay.Keys.media_type)
    val service_version = jsonObject.getString(ImageOfTheDay.Keys.service_version)
    val title = jsonObject.getString(ImageOfTheDay.Keys.title)
    val url = jsonObject.getString(ImageOfTheDay.Keys.url)

    return ImageOfTheDay(copyright, date, explanation, hdurl, media_type, service_version, title, url)
}