package com.wiryadev.jakartavaxavailability.data.response


import com.google.gson.annotations.SerializedName

data class VaccineResponseItem(
    @SerializedName("alamat_lokasi_vaksinasi")
    val alamatLokasiVaksinasi: String?,
    @SerializedName("jadwal")
    val jadwal: List<Jadwal>,
    @SerializedName("jenis_faskes")
    val jenisFaskes: String?,
    @SerializedName("jumlah_tim_vaksinator")
    val jumlahTimVaksinator: Int,
    @SerializedName("kecamatan")
    val kecamatan: String,
    @SerializedName("kelurahan")
    val kelurahan: String,
    @SerializedName("kode_lokasi_vaksinasi")
    val kodeLokasiVaksinasi: Int?,
    @SerializedName("kodepos")
    val kodepos: String?,
    @SerializedName("last_updated_at")
    val lastUpdatedAt: String,
    @SerializedName("nama_lokasi_vaksinasi")
    val namaLokasiVaksinasi: String,
    @SerializedName("open_regis")
    val openRegis: String?,
    @SerializedName("wilayah")
    val wilayah: String
)