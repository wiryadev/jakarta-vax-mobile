package com.wiryadev.jakartavaxavailability.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
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
    return System.currentTimeMillis() - this.time
}

/**
    @return  amount of minutes, in Int
             from given milliseconds value
 */
fun Long.getMinutes(): Int {
    return TimeUnit.MILLISECONDS.toMinutes(this).toInt()
}

/** Check if given value is null or empty
 *  if so, return long dash
 *  otherwise, return itself
 */
fun Any?.returnDashIfNull(): String {
    return this?.toString() ?: "—"
}

// return placeholder modifier with shimmer highlight
fun Modifier.shimmerPlaceholder(visible: Boolean): Modifier {
    return composed {
        placeholder(
            visible = visible,
            highlight = PlaceholderHighlight.shimmer()
        )
    }
}