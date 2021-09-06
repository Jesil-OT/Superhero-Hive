package com.segunfrancis.data.remote.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("lg")
    val largeImage: String,

    @SerializedName("md")
    val mediumImage: String,

    @SerializedName("sm")
    val smallImage: String,

    @SerializedName("xs")
    val extraSmallImage: String
)