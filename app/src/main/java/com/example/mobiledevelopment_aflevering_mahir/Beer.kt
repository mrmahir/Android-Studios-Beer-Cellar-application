package com.example.mobiledevelopment_aflevering_mahir

import com.google.gson.annotations.SerializedName
data class Beer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    val user: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("brewery")
    val brewery: String,
    @SerializedName("style")
    val style: String,
    @SerializedName("abv")
    val abv: Double,
    @SerializedName("volume")
    val volume: Double?,
    @SerializedName("pictureUrl")
    val pictureUrl: String?,
    @SerializedName("howMany")
    val howMany: Int
)