package com.udacity.asteroidradar.main

import android.net.Network
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.network.NasaApi
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber

const val KEY = ""

class MainViewModel : ViewModel() {

    init {
        viewModelScope.launch {

            try {
//                Timber.e("imageOfTheDay")
//                val imageOfTheDay = NasaApi.retrofitService.getImageOfTheDay(KEY)
//                Timber.e(imageOfTheDay.toString())
                Timber.e("resultList")
                val resultList = NasaApi.retrofitService.getAsteroids("2021-07-05", KEY)
//                val resultList = NasaApi.retrofitService.getAsteroids("2021-07-12", "2021-07-15", KEY)
                Timber.e(resultList)
                val asteroidList = parseAsteroidsJsonResult(JSONObject(resultList))
                asteroidList.forEach {
                    Timber.e(it.toString())
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}