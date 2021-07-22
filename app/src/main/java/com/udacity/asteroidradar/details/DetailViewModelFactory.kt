package com.udacity.asteroidradar.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.Asteroid

class DetailViewModelFactory(val asteroid: Asteroid) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(asteroid) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
