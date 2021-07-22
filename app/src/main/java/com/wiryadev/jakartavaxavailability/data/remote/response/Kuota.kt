package com.wiryadev.jakartavaxavailability.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Kuota(
   @Json(name = "sisaKuota")
    val sisaKuota: Int,
   @Json(name = "totalKuota")
    val totalKuota: Int,
)