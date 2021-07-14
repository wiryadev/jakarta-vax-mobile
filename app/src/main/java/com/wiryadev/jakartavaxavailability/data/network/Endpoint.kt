package com.wiryadev.jakartavaxavailability.data.network

import com.wiryadev.jakartavaxavailability.data.response.VaccineResponses
import retrofit2.http.GET

interface Endpoint {
    @GET(".")
    suspend fun getVaccines(): VaccineResponses
}