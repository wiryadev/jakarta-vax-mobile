package com.wiryadev.jakartavaxavailability.data

import com.wiryadev.jakartavaxavailability.data.network.ApiService
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VaccineRepository @Inject constructor(private val service: ApiService) {

    private var cachedList: List<VaccineResponseItem> = emptyList()

    suspend fun getVaccines(): List<VaccineResponseItem> {
        var cachedList = cachedList
        if (cachedList.isEmpty()) {
            cachedList = service.getVaccines().results
            this.cachedList = cachedList
        }
        return cachedList
    }

}