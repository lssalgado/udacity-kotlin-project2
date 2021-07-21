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
        val UPDATE = "RefreshDataWork"
        val DELETE = "RefreshDataWork"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidsRepository(database)

        return try {
            repository.refreshAsteroids()
            Timber.e("Successfully refreshed asteroids!!")
            Result.success()
        } catch (e: HttpException) {
            Timber.e("Could not refreshed asteroids!!")
            Timber.e(e)
            Result.retry()
        }
    }

}