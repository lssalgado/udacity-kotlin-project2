package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.network.pictureOfDayFromString
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber

const val KEY = BuildConfig.NASA_API_KEY

class MainViewModel : ViewModel() {

    private val pictureOfDay_ = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = pictureOfDay_

    init {
        viewModelScope.launch {

            try {
                val imageResult = NasaApi.retrofitService.getPictureOfDay(KEY)
                pictureOfDay_.value = pictureOfDayFromString(imageResult)
                val resultList =
                    NasaApi.retrofitService.getAsteroids("2021-07-12", "2021-07-15", KEY)
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