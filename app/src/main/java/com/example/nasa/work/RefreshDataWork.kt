package com.example.nasa.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.nasa.database.getDataBase
import com.example.nasa.repository.AsteroidRepository
import com.example.nasa.util.Constants
import java.util.Calendar
import java.util.Date

class RefreshDataWork(
    private val appContext: Context,
    private val params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "refreshAsteroidsWork"
    }

    override suspend fun doWork(): Result {
        val database = getDataBase(appContext)
        val repository = AsteroidRepository(database)
        repository.refreshAsteroids()
        return try {
            Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }
}