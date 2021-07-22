package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.network.pictureOfDayFromString
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

const val KEY = BuildConfig.NASA_API_KEY

class MainViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val repository = AsteroidsRepository(database)

    private val pictureOfDay_ = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = pictureOfDay_

    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid>()
    val navigateToAsteroidDetails
        get() = _navigateToAsteroidDetails

    init {
        viewModelScope.launch {
            try {
                val imageResult = NasaApi.retrofitService.getPictureOfDay(KEY)
                pictureOfDay_.value = pictureOfDayFromString(imageResult)
                repository.refreshAsteroids()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    val asteroids = repository.asteroids

    fun onAsteroidClicked(id: Long) {
        _navigateToAsteroidDetails.value = asteroids.value?.find { it.id == id }
    }

    fun onAsteroidDetailsNavigated() {
        _navigateToAsteroidDetails.value = null
    }
}