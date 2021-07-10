package com.wiryadev.jakartavaxavailability.utils


fun String.capitalizeWords(): String = split(" ").joinToString(" ") { word ->
    word.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}