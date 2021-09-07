package com.segunfrancis.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroModel(
    val appearance: Appearance,

    val biography: Biography,

    val connections: Connections,

    val id: Int,

    val images: Images,

    val name: String,

    val powerStats: Powerstats,

    val work: Work,

    var isFavorite : Boolean = false
) : Parcelable
