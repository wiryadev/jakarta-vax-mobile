package com.wiryadev.jakartavaxavailability.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun String.capitalizeWords(): String = split(" ").joinToString(" ") { word ->
    word.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

/**
    @return  formatted date from given String
 */
fun String.toDate(): Date? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return dateFormat.parse(this)
}

/**
    @return  the difference, measure in milliseconds
             between current system time and the time passed to the function
 */
fun Date.compareToCurrentDateTime(): Long {
    val thisTime: Long = this.time
    val currentTime: Long = System.currentTimeMillis()
    return currentTime - thisTime
}

/**
    @return  amount of minutes, in Int
             from given milliseconds value
 */
fun Long.getMinutes(): Int {
    return TimeUnit.MILLISECONDS.toMinutes(this).toInt()
}

/** Check if given string is null or empty
 *  if so, return long dash
 *  otherwise, return itself
 */
fun CharSequence?.returnDashIfNullOrEmpty(): String {
    return if (!this.isNullOrEmpty()) {
        this.toString()
    } else {
        "—"
    }
}