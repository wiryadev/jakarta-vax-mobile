package com.wiryadev.jakartavaxavailability.data.response


import com.google.gson.annotations.SerializedName

data class DetailLokasi(
    @SerializedName("boundingbox")
    val boundingbox: List<String>,
    @SerializedName("category")
    val category: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("importance")
    val importance: Double,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("licence")
    val licence: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("osm_id")
    val osmId: Int,
    @SerializedName("osm_type")
    val osmType: String,
    @SerializedName("place_id")
    val placeId: Int,
    @SerializedName("place_rank")
    val placeRank: Int,
    @SerializedName("type")
    val type: String
)