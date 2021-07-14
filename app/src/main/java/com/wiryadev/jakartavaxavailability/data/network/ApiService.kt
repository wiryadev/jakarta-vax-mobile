package com.wiryadev.jakartavaxavailability.data.network

import com.wiryadev.jakartavaxavailability.data.response.VaccineResponses
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(private val endpoint: Endpoint) {
    companion object {
        const val BASE_URL = "https://vaksin-jakarta.yggdrasil.id/"
    }

    suspend fun getVaccines(): VaccineResponses = endpoint.getVaccines()
}