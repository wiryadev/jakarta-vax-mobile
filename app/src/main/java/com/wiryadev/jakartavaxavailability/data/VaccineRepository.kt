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

    fun searchFromList(query: String, searchType: SearchType): List<VaccineResponseItem> {
        return when (searchType) {
            SearchType.LOKASI -> searchBasedOnLokasi(query)
            SearchType.KECAMATAN -> searchBasedOnKecamatan(query)
            SearchType.KELURAHAN -> searchBasedOnKelurahan(query)
        }
    }

    private fun searchBasedOnLokasi(query: String): List<VaccineResponseItem> {
        return cachedList.filter { item ->
            item.namaLokasiVaksinasi.contains(query, ignoreCase = true)
        }
    }

    private fun searchBasedOnKecamatan(query: String): List<VaccineResponseItem> {
        return cachedList.filter { item ->
            item.kecamatan.contains(query, ignoreCase = true)
        }
    }

    private fun searchBasedOnKelurahan(query: String): List<VaccineResponseItem> {
        return cachedList.filter { item ->
            item.kelurahan.contains(query, ignoreCase = true)
        }
    }

}