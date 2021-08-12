package com.jesil.toborowei.hive.superherohive.model.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Work(
    val base: String,
    val occupation: String
) : Parcelable