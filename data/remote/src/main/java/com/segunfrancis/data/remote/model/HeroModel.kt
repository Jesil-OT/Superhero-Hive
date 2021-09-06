package com.segunfrancis.data.remote.model

import com.google.gson.annotations.SerializedName

data class HeroModel(
    @SerializedName("appearance")
    val appearance: Appearance,

    @SerializedName("biography")
    val biography: Biography,

    @SerializedName("connections")
    val connections: Connections,

    @SerializedName("id")
    val id: Int,

    @SerializedName("images")
    val images: Images,

    @SerializedName("name")
    val name: String,

    @SerializedName("powerstats")
    val powerStats: Powerstats,

    @SerializedName("work")
    val work: Work,

    var isFavorite : Boolean = false
)
