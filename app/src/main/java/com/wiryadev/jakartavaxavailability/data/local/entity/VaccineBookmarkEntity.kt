package com.wiryadev.jakartavaxavailability.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class VaccineBookmarkEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nama_lokasi_vaksinasi")
    val namaLokasiVaksinasi: String,

    @ColumnInfo(name = "kecamatan")
    val kecamatan: String,

    @ColumnInfo(name = "kelurahan")
    val kelurahan: String,


    @ColumnInfo(name ="wilayah")
    val wilayah: String,
)
