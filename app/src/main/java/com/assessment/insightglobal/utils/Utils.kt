package com.assessment.insightglobal.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun formatDate(dateValue: String?): String {
        val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        var formattedDate = ""
        try {
            dateValue?.let {
                val date: Date = apiDateFormat.parse(it)!!
                val desiredDateFormat = SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.getDefault())
                formattedDate = desiredDateFormat.format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return formattedDate
    }
}