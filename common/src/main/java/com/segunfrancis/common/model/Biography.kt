package com.segunfrancis.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Biography(
    val aliases: List<String>,

    val firstAppearance: String,

    val fullName: String,

    val placeOfBirth: String,

    val publisher: String?
) : Parcelable