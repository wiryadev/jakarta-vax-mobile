package com.wiryadev.jakartavaxavailability.data.response


import com.google.gson.annotations.SerializedName

data class Jadwal(
    @SerializedName("id")
    val id: String,
    @SerializedName("waktu")
    val waktu: List<Waktu>
)