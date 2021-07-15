package com.wiryadev.jakartavaxavailability.data.remote

import com.wiryadev.jakartavaxavailability.data.remote.network.ApiService
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem
import com.wiryadev.jakartavaxavailability.ui.home.SearchType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val service: ApiService) {

    private var cachedList: List<VaccineResponseItem> = emptyList()

    suspend fun getVaccines(
        isRefreshing: Boolean,
        query: String,
        searchType: SearchType,
    ): List<VaccineResponseItem> {

        if (isRefreshing) {
            this.cachedList = emptyList()
        }

        var cachedList = cachedList

        if (cachedList.isEmpty()) {
            cachedList = service.getVaccines()
            this.cachedList = cachedList
        }

        return if (query.isEmpty()) {
            cachedList
        } else {
            searchFromList(query = query, searchType = searchType)
        }

    }

    suspend fun getLocationByName(name: String, isRefreshing: Boolean): VaccineResponseItem {
        val list = if (!isRefreshing) cachedList else service.getVaccines()
        return list.first { item ->
            item.namaLokasiVaksinasi == name
        }
    }

    private fun searchFromList(query: String, searchType: SearchType): List<VaccineResponseItem> {
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