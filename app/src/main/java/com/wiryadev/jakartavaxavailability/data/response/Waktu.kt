package com.wiryadev.jakartavaxavailability.data.response


import com.google.gson.annotations.SerializedName

data class Waktu(
    @SerializedName("id")
    val id: String,
    @SerializedName("kuota")
    val kuota: Kuota
)