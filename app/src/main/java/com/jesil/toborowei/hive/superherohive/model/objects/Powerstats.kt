package com.jesil.toborowei.hive.superherohive.model.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Powerstats(
    @SerializedName("combat")
    val combat: Int,

    @SerializedName("durability")
    val durability: Int,

    @SerializedName("intelligence")
    val intelligence: Int,

    @SerializedName("power")
    val power: Int,

    @SerializedName("speed")
    val speed: Int,

    @SerializedName("strength")
    val strength: Int
) : Parcelable