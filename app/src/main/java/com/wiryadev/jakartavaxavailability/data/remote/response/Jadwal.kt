package com.wiryadev.jakartavaxavailability.data.remote.response


import com.google.gson.annotations.SerializedName

data class Jadwal(
    @SerializedName("id")
    val id: String,
    @SerializedName("waktu")
    val waktu: List<Waktu>,
)