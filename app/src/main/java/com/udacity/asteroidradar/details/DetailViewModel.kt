package com.udacity.asteroidradar.details

import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class DetailViewModel(private val asteroid: Asteroid): ViewModel() {
    fun getAsteroid(): Asteroid = asteroid
}
