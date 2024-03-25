package com.example.nasa.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"

    const val API_KEY = "YEmXqs6dIX8iBCHrPPYlnahHVDWdMcAa7WcqxsmX"


    val today = Date()


    fun afterSevenDays(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = today
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        return calendar.time
    }

    fun formatAsteroidDate(date: Date): String {
        val dateFormat = SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun getCurrentDateFormatted(): String {
        return formatAsteroidDate(Date())
    }

    fun getAfterSevenDaysFormatted(): String {
        return formatAsteroidDate(afterSevenDays())
    }
}