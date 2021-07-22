package com.wiryadev.jakartavaxavailability.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VaccineResponseItem(
   @Json(name = "alamat_lokasi_vaksinasi")
    val alamatLokasiVaksinasi: String?,
   @Json(name = "jadwal")
    val jadwal: List<Jadwal>,
   @Json(name = "jenis_faskes")
    val jenisFaskes: String?,
   @Json(name = "jumlah_tim_vaksinator")
    val jumlahTimVaksinator: Int?,
   @Json(name = "kecamatan")
    val kecamatan: String,
   @Json(name = "kelurahan")
    val kelurahan: String,
   @Json(name = "kode_lokasi_vaksinasi")
    val kodeLokasiVaksinasi: Int?,
   @Json(name = "kodepos")
    val kodepos: String?,
   @Json(name = "last_updated_at")
    val lastUpdatedAt: String,
   @Json(name = "nama_lokasi_vaksinasi")
    val namaLokasiVaksinasi: String,
   @Json(name = "open_regis")
    val openRegis: String?,
   @Json(name = "wilayah")
    val wilayah: String
)