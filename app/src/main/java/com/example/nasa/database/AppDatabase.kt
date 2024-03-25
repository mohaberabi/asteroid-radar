package com.example.nasa.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nasa.model.Asteroid
import java.util.Date

@Dao
interface AsteroidDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asteroid: AsteroidDBModel)

    @Query("select * from asteroids")
    fun getAsteroids(): LiveData<List<AsteroidDBModel>>

    @Query("select * from asteroids where closeApproachDate between date(:startDate) and date(:endDate) ORDER BY closeApproachDate")
    fun getWeekAsteroid(startDate: String, endDate: String): LiveData<List<AsteroidDBModel>>

    @Query("select * from asteroids where closeApproachDate == date(:startDate)")
    fun getTodayAsteroid(startDate: String): LiveData<List<AsteroidDBModel>>

}

@Dao
interface ImageOfDayDao {
    @Query("SELECT * FROM imgOfDay LIMIT 1")
    fun getImageOfDay(): LiveData<ImageOfDayDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imgOfDay: ImageOfDayDbModel)
}


@Database(
    entities = [AsteroidDBModel::class,
        ImageOfDayDbModel::class],
    version = 1,
)
abstract class AsteroidDataBase : RoomDatabase() {

    abstract val asteroidDao: AsteroidDao
    abstract val imgOfDayDao: ImageOfDayDao

}


private lateinit var INSTANCE: AsteroidDataBase


fun getDataBase(context: Context): AsteroidDataBase {
    if (!::INSTANCE.isInitialized) {
        INSTANCE =
            Room.databaseBuilder(
                context.applicationContext,
                AsteroidDataBase::class.java,
                "nasaDb",
            )
                .build()
    }

    return INSTANCE
}