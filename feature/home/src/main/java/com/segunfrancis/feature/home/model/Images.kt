package com.segunfrancis.feature.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    @SerializedName("lg")
    val largeImage: String,

    @SerializedName("md")
    val mediumImage: String,

    @SerializedName("sm")
    val smallImage: String,

    @SerializedName("xs")
    val extraSmallImage: String
) : Parcelable