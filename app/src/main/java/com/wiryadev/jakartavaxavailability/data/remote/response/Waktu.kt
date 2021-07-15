package com.wiryadev.jakartavaxavailability.data.remote.response


import com.google.gson.annotations.SerializedName

data class Waktu(
    @SerializedName("id")
    val id: String,
    @SerializedName("kuota")
    val kuota: Kuota?,
)