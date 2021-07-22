package com.wiryadev.jakartavaxavailability.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Jadwal(
   @Json(name = "id")
    val id: String,
   @Json(name = "waktu")
    val waktu: List<Waktu>,
)