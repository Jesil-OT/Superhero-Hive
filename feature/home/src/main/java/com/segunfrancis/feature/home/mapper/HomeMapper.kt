package com.segunfrancis.feature.home.mapper

import com.segunfrancis.common.model.Appearance
import com.segunfrancis.common.model.Biography
import com.segunfrancis.common.model.Connections
import com.segunfrancis.common.model.HeroModel
import com.segunfrancis.common.model.Images
import com.segunfrancis.common.model.Powerstats
import com.segunfrancis.common.model.Work
import com.segunfrancis.domain.model.AppearanceDomain
import com.segunfrancis.domain.model.BiographyDomain
import com.segunfrancis.domain.model.ConnectionsDomain
import com.segunfrancis.domain.model.HeroModelDomain
import com.segunfrancis.domain.model.ImagesDomain
import com.segunfrancis.domain.model.PowerstatsDomain
import com.segunfrancis.domain.model.WorkDomain

private fun WorkDomain.mapToWorkHome(): Work {
    return Work(base, occupation)
}

private fun PowerstatsDomain.mapToPowerStatsHome(): Powerstats {
    return Powerstats(combat, durability, intelligence, power, speed, strength)
}

private fun ImagesDomain.mapToImagesHome(): Images {
    return Images(largeImage, mediumImage, smallImage, extraSmallImage)
}

private fun ConnectionsDomain.mapToConnectionsHome(): Connections {
    return Connections(groupAffiliation, relatives)
}

private fun BiographyDomain.mapToBiographyHome(): Biography {
    return Biography(aliases, firstAppearance, fullName, placeOfBirth, publisher)
}

private fun AppearanceDomain.mapToAppearanceHome(): Appearance {
    return Appearance(eyeColor, gender, hairColor, height, race, weight)
}

fun HeroModelDomain.mapToHeroHome(): HeroModel {
    return HeroModel(
        id = id,
        name = name,
        appearance = appearanceDomain.mapToAppearanceHome(),
        biography = biographyDomain.mapToBiographyHome(),
        connections = connectionsDomain.mapToConnectionsHome(),
        images = imagesDomain.mapToImagesHome(),
        powerStats = powerStats.mapToPowerStatsHome(),
        work = workDomain.mapToWorkHome()
    )
}
