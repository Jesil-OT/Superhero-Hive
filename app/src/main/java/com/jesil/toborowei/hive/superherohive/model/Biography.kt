package com.jesil.toborowei.hive.superherohive.model

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Biography(
    @SerializedName("aliases")
    val aliases: List<String>,

    @SerializedName("firstAppearance")
    val firstAppearance: String,

    @SerializedName("fullName")
    val fullName: String,

    @SerializedName("placeOfBirth")
    val placeOfBirth: String,

    @SerializedName("publisher")
    val publisher: String?
) : Parcelable