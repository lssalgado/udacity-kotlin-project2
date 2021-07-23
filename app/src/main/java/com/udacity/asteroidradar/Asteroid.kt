package com.udacity.asteroidradar

import android.content.Context
import android.os.Parcelable
import com.udacity.asteroidradar.database.DatabaseAsteroid
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
    val id: Long, val codename: String, val closeApproachDate: String,
    val absoluteMagnitude: Double, val estimatedDiameter: Double,
    val relativeVelocity: Double, val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable {
    fun getContentDescription(context: Context): String {
        return if (isPotentiallyHazardous) {
            context.getString(R.string.potentially_hazardous)
        } else {
            context.getString(R.string.not_hazardous)
        }
    }
}

fun List<Asteroid>.asDatabaseModel(): Array<DatabaseAsteroid> {
    return map {
        DatabaseAsteroid(
            it.id,
            it.codename,
            it.closeApproachDate,
            it.absoluteMagnitude,
            it.estimatedDiameter,
            it.relativeVelocity,
            it.distanceFromEarth,
            it.isPotentiallyHazardous
        )
    }.toTypedArray()
}
