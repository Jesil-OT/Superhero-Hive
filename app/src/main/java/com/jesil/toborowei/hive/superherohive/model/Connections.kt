package com.jesil.toborowei.hive.superherohive.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Connections(
    @SerializedName("groupAffiliation")
    val groupAffiliation: String,

    @SerializedName("relatives")
    val relatives: String
) : Parcelable