package com.wiryadev.jakartavaxavailability.data.remote.response


import com.google.gson.annotations.SerializedName

data class Kuota(
    @SerializedName("sisaKuota")
    val sisaKuota: Int,
    @SerializedName("totalKuota")
    val totalKuota: Int,
)