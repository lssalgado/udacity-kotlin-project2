package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import retrofit2.HttpException
import timber.log.Timber

class UpdateAsteroidsWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        val NAME = "RefreshAsteroidsWork"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidsRepository(database)

        return try {
            repository.deleteOldAsteroids()
            repository.refreshAsteroids()
            Timber.e("Successfully refreshed asteroids!!")
            Result.success()
        } catch (e: HttpException) {
            Timber.e("Could not refresh asteroids!!")
            Timber.e(e)
            Result.retry()
        }
    }

}