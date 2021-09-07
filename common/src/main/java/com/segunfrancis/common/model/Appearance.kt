package com.segunfrancis.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appearance(
    val eyeColor: String,

    val gender: String,

    val hairColor: String,

    val height: List<String>,

    val race: String?,

    val weight: List<String>
): Parcelable
