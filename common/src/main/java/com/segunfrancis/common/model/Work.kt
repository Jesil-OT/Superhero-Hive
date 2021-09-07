package com.segunfrancis.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Work(
    val base: String,

    val occupation: String
) : Parcelable