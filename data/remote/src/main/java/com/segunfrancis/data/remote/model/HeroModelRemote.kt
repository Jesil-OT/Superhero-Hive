package com.segunfrancis.data.remote.model

import com.google.gson.annotations.SerializedName

data class HeroModelRemote(
    @SerializedName("appearance")
    val appearanceRemote: AppearanceRemote,

    @SerializedName("biography")
    val biographyRemote: BiographyRemote,

    @SerializedName("connections")
    val connectionsRemote: ConnectionsRemote,

    @SerializedName("id")
    val id: Int,

    @SerializedName("images")
    val imagesRemote: ImagesRemote,

    @SerializedName("name")
    val name: String,

    @SerializedName("powerstats")
    val powerStats: PowerstatsRemote,

    @SerializedName("work")
    val workRemote: WorkRemote,
)
