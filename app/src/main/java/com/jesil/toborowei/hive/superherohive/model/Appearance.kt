package com.jesil.toborowei.hive.superherohive.model

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appearance(
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
): Parcelable