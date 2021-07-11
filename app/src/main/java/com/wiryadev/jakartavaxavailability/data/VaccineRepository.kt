package com.wiryadev.jakartavaxavailability.data

import com.wiryadev.jakartavaxavailability.data.network.ApiService
import com.wiryadev.jakartavaxavailability.data.response.VaccineResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VaccineRepository @Inject constructor(private val service: ApiService) {

    private var cachedList: List<VaccineResponseItem> = emptyList()

    suspend fun getVaccines(isRefreshing: Boolean): List<VaccineResponseItem> {
        var cachedList = cachedList

        if (isRefreshing) {
            this.cachedList = emptyList()
        }

        if (cachedList.isEmpty()) {
            cachedList = service.getVaccines()
            this.cachedList = cachedList
        }

        return cachedList
    }

}