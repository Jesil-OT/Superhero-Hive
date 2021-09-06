package com.segunfrancis.data.remote.model

import com.google.gson.annotations.SerializedName

data class AppearanceRemote(
    @SerializedName("eyeColor")
    val eyeColor: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("hairColor")
    val hairColor: String,

    @SerializedName("height")
    val height: List<String>,

    @SerializedName("race")
    val race: String?,

    @SerializedName("weight")
    val weight: List<String>
)