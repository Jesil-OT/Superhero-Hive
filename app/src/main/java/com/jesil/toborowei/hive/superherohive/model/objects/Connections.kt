package com.jesil.toborowei.hive.superherohive.model.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Connections(
    val groupAffiliation: String,
    val relatives: String
) : Parcelable