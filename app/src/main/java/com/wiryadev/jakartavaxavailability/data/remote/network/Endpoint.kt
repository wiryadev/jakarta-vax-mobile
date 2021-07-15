package com.wiryadev.jakartavaxavailability.data.remote.network

import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem
import retrofit2.http.GET

interface Endpoint {
    @GET(".")
    suspend fun getVaccines(): List<VaccineResponseItem>
}