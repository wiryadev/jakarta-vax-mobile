package com.wiryadev.jakartavaxavailability.utils

import java.util.*

fun String.capitalizeWords(): String = split(" ").map { word ->
    word.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}.joinToString(" ")