package com.segunfrancis.data.remote.model

import com.google.gson.annotations.SerializedName

data class BiographyRemote(
    @SerializedName("aliases")
    val aliases: List<String>,

    @SerializedName("firstAppearance")
    val firstAppearance: String,

    @SerializedName("fullName")
    val fullName: String,

    @SerializedName("placeOfBirth")
    val placeOfBirth: String,

    @SerializedName("publisher")
    val publisher: String?
)