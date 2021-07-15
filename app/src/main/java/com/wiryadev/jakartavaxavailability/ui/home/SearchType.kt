package com.wiryadev.jakartavaxavailability.ui.home

enum class SearchType(val value: String) {
    LOKASI("Nama Lokasi"),
    KECAMATAN("Kecamatan"),
    KELURAHAN("Kelurahan"),
}

fun getSearchTypes(): List<SearchType> {
    return listOf(SearchType.LOKASI, SearchType.KECAMATAN, SearchType.KELURAHAN)
}