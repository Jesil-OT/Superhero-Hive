package com.segunfrancis.domain.model

data class HeroModelDomain(
    val appearanceDomain: AppearanceDomain,

    val biographyDomain: BiographyDomain,

    val connectionsDomain: ConnectionsDomain,

    val id: Int,

    val imagesDomain: ImagesDomain,

    val name: String,

    val powerStats: PowerstatsDomain,

    val workDomain: WorkDomain,

    var isFavorite : Boolean = false
)
