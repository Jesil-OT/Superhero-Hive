package com.jesil.toborowei.hive.superherohive.model.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Powerstats(
    val combat: Int,
    val durability: Int,
    val intelligence: Int,
    val power: Int,
    val speed: Int,
    val strength: Int
) : Parcelable