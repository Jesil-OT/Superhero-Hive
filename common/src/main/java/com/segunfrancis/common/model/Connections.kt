package com.segunfrancis.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Connections(
    val groupAffiliation: String,

    val relatives: String
) : Parcelable
