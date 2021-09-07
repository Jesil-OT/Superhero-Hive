package com.segunfrancis.data.local.mapper

import com.segunfrancis.data.local.model.AppearanceLocal
import com.segunfrancis.data.local.model.BiographyLocal
import com.segunfrancis.data.local.model.ConnectionsLocal
import com.segunfrancis.data.local.model.HeroModelLocal
import com.segunfrancis.data.local.model.ImagesLocal
import com.segunfrancis.data.local.model.PowerstatsLocal
import com.segunfrancis.data.local.model.WorkLocal
import com.segunfrancis.domain.model.AppearanceDomain
import com.segunfrancis.domain.model.BiographyDomain
import com.segunfrancis.domain.model.ConnectionsDomain
import com.segunfrancis.domain.model.HeroModelDomain
import com.segunfrancis.domain.model.ImagesDomain
import com.segunfrancis.domain.model.PowerstatsDomain
import com.segunfrancis.domain.model.WorkDomain

/**
 * Extensions for mapping local to domain layer data classes
 */
private fun WorkLocal.mapToWorkDomain(): WorkDomain {
    return WorkDomain(base, occupation)
}

private fun PowerstatsLocal.mapToPowerStatsDomain(): PowerstatsDomain {
    return PowerstatsDomain(combat, durability, intelligence, power, speed, strength)
}

private fun ImagesLocal.mapToImagesDomain(): ImagesDomain {
    return ImagesDomain(largeImage, mediumImage, smallImage, extraSmallImage)
}

private fun ConnectionsLocal.mapToConnectionsDomain(): ConnectionsDomain {
    return ConnectionsDomain(groupAffiliation, relatives)
}

private fun BiographyLocal.mapToBiographyDomain(): BiographyDomain {
    return BiographyDomain(aliases, firstAppearance, fullName, placeOfBirth, publisher)
}

private fun AppearanceLocal.mapToAppearanceDomain(): AppearanceDomain {
    return AppearanceDomain(eyeColor, gender, hairColor, height, race, weight)
}

fun HeroModelLocal.mapToHeroDomain(): HeroModelDomain {
    return HeroModelDomain(
        id = id,
        name = name,
        isFavorite = isFavorite,
        appearanceDomain = appearanceLocal.mapToAppearanceDomain(),
        biographyDomain = biographyLocal.mapToBiographyDomain(),
        connectionsDomain = connectionsLocal.mapToConnectionsDomain(),
        imagesDomain = imagesLocal.mapToImagesDomain(),
        powerStats = powerStats.mapToPowerStatsDomain(),
        workDomain = workLocal.mapToWorkDomain()
    )
}

/**
 * Extensions for mapping domain to local layer data classes
 */
private fun WorkDomain.mapToWorkLocal(): WorkLocal {
    return WorkLocal(base, occupation)
}

private fun PowerstatsDomain.mapToPowerStatsLocal(): PowerstatsLocal {
    return PowerstatsLocal(combat, durability, intelligence, power, speed, strength)
}

private fun ImagesDomain.mapToImagesLocal(): ImagesLocal {
    return ImagesLocal(largeImage, mediumImage, smallImage, extraSmallImage)
}

private fun ConnectionsDomain.mapToConnectionsLocal(): ConnectionsLocal {
    return ConnectionsLocal(groupAffiliation, relatives)
}

private fun BiographyDomain.mapToBiographyLocal(): BiographyLocal {
    return BiographyLocal(aliases, firstAppearance, fullName, placeOfBirth, publisher)
}

private fun AppearanceDomain.mapToAppearanceLocal(): AppearanceLocal {
    return AppearanceLocal(eyeColor, gender, hairColor, height, race, weight)
}

fun HeroModelDomain.mapToHeroLocal(): HeroModelLocal {
    return HeroModelLocal(
        id = id,
        name = name,
        isFavorite = isFavorite,
        appearanceLocal = appearanceDomain.mapToAppearanceLocal(),
        biographyLocal = biographyDomain.mapToBiographyLocal(),
        connectionsLocal = connectionsDomain.mapToConnectionsLocal(),
        imagesLocal = imagesDomain.mapToImagesLocal(),
        powerStats = powerStats.mapToPowerStatsLocal(),
        workLocal = workDomain.mapToWorkLocal()
    )
}
