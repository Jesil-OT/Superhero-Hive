package com.jesil.toborowei.hive.superherohive.model.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Biography(
    val aliases: List<String>,
    val alignment: String,
    val firstAppearance: String,
    val fullName: String,
    val placeOfBirth: String,
    val publisher: String
) : Parcelable