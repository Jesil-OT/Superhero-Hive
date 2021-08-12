package com.jesil.toborowei.hive.superherohive.model.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appearance(
    val eyeColor: String,
    val gender: String,
    val hairColor: String,
    val height: List<String>,
    val race: String,
    val weight: List<String>
): Parcelable