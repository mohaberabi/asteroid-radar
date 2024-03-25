package com.example.nasa.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.nasa.database.AsteroidDataBase
import com.example.nasa.database.toAsteroid
import com.example.nasa.database.toImageOfDay
import com.example.nasa.model.Asteroid
import com.example.nasa.model.ImageOfDay
import com.example.nasa.model.toDatabaseAsteroid
import com.example.nasa.network.AsteroidsServices
import com.example.nasa.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository(private val database: AsteroidDataBase) {

    suspend fun refreshImageOfDay(): ImageOfDay {
        return withContext(Dispatchers.IO) {
            return@withContext AsteroidsServices.fetchImageOfTheDay()
        }
    }

    suspend fun refreshAsteroids(
        startDate: String = Constants.getCurrentDateFormatted(),
        endDate: String = Constants.getAfterSevenDaysFormatted()
    ) {
        withContext(Dispatchers.IO) {
            val asteroids =
                AsteroidsServices.fetchAsteroids(
                    startDate = startDate,
                    endDate = endDate
                )

            if (asteroids != null) {
                for (asto in asteroids) {
                    database.asteroidDao.insert(asto.toDatabaseAsteroid())
                }


            } else {
                Log.e("refreshAsteroids", "Failed to fetch asteroids: }")

            }


        }
    }


    val asteroidOfToday: LiveData<List<Asteroid>> =
        database.asteroidDao.getTodayAsteroid(Constants.getCurrentDateFormatted()).map {

            it.map { ast ->
                ast.toAsteroid()
            }
        }

    val asteroidOfWeek: LiveData<List<Asteroid>> =
        database.asteroidDao.getWeekAsteroid(
            Constants.getCurrentDateFormatted(),
            Constants.getAfterSevenDaysFormatted()
        ).map {
            it.map { ast ->
                ast.toAsteroid()
            }
        }

    val asteroids: LiveData<List<Asteroid>> = database.asteroidDao.getAsteroids(
    ).map {
        it.map { ast ->
            ast.toAsteroid()
        }
    }
}