package com.wiryadev.jakartavaxavailability.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Waktu(
   @Json(name = "id")
    val id: String,
   @Json(name = "kuota")
    val kuota: Kuota?,
)