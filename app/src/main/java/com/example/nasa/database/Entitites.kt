package com.example.nasa.database

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nasa.model.Asteroid
import com.example.nasa.model.ImageOfDay
import com.example.nasa.model.toDbModel

@Entity(tableName = "imgOfDay")
data class ImageOfDayDbModel(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val mediaType: String,
    val title: String,
)

fun ImageOfDayDbModel.toImageOfDay(): ImageOfDay {

    return ImageOfDay(
        url = this.url,
        mediaType = this.mediaType,
        title = this.title,
    )
}


@Entity(tableName = "asteroids")
data class AsteroidDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val codename: String,

    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)


fun AsteroidDBModel.toAsteroid(): Asteroid {
    return Asteroid(
        id = this.id,
        codename = this.codename,
        closeApproachDate = this.closeApproachDate,
        absoluteMagnitude = this.absoluteMagnitude,
        estimatedDiameter = this.estimatedDiameter,
        relativeVelocity = this.relativeVelocity,
        distanceFromEarth = this.distanceFromEarth,
        isPotentiallyHazardous = this.isPotentiallyHazardous
    )
}