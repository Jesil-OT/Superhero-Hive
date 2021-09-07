package com.segunfrancis.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val largeImage: String,

    val mediumImage: String,

    val smallImage: String,

    val extraSmallImage: String
) : Parcelable