package com.example.nasa.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.example.nasa.database.AsteroidDBModel

data class Asteroid(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    )


    override fun describeContents(): Int {
        return 0
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(codename)
        parcel.writeString(closeApproachDate)
        parcel.writeDouble(absoluteMagnitude)
        parcel.writeDouble(estimatedDiameter)
        parcel.writeDouble(relativeVelocity)
        parcel.writeDouble(distanceFromEarth)
        parcel.writeBoolean(isPotentiallyHazardous)

    }

    companion object CREATOR : Parcelable.Creator<Asteroid> {
        override fun createFromParcel(parcel: Parcel): Asteroid {
            return Asteroid(parcel)
        }

        override fun newArray(size: Int): Array<Asteroid?> {
            return arrayOfNulls(size)
        }
    }
}

fun Asteroid.toDatabaseAsteroid(): AsteroidDBModel {
    return AsteroidDBModel(
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