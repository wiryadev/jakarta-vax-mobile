package com.wiryadev.jakartavaxavailability.utils

import com.wiryadev.jakartavaxavailability.data.remote.response.Jadwal
import com.wiryadev.jakartavaxavailability.data.remote.response.VaccineResponseItem
import com.wiryadev.jakartavaxavailability.data.remote.response.Waktu

object DummyPlaceholder {
    private val dummyList = mutableListOf<VaccineResponseItem>()

    private val dummyTime = Waktu(
        id = "09:00:00",
        kuota = null,
    )

    private val dummyResponseItem = VaccineResponseItem(
        alamatLokasiVaksinasi = null,
        namaLokasiVaksinasi = "Nama Tempat/Lokasi Vaksinasi",
        kecamatan = "Kecamatan",
        kelurahan = "Kelurahan",
        wilayah = "Nama Wilayah",
        jenisFaskes = "Jenis Faskes",
        kodeLokasiVaksinasi = 1234,
        lastUpdatedAt = "2021-07-15T19:45:01.891381",
        openRegis = "openRegis",
        jumlahTimVaksinator = 10,
        kodepos = "10101",
        jadwal = listOf(
            Jadwal(id = "2021-07-17", waktu = listOf(dummyTime)),
            Jadwal(id = "2021-07-17", waktu = listOf(dummyTime)),
            Jadwal(id = "2021-07-17", waktu = listOf(dummyTime)),
        ),
    )

    fun getDummyList(): List<VaccineResponseItem> {
        repeat(5) {
            dummyList.add(dummyResponseItem)
        }
        return dummyList
    }
}