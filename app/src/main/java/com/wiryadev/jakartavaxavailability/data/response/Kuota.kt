package com.wiryadev.jakartavaxavailability.data.response


import com.google.gson.annotations.SerializedName

data class Kuota(
    @SerializedName("jakiKuota")
    val jakiKuota: String,
    @SerializedName("sisaKuota")
    val sisaKuota: Int,
    @SerializedName("totalKuota")
    val totalKuota: Int
)