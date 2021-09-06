package com.segunfrancis.data.remote.mapper

import com.segunfrancis.data.remote.model.AppearanceRemote
import com.segunfrancis.data.remote.model.BiographyRemote
import com.segunfrancis.data.remote.model.ConnectionsRemote
import com.segunfrancis.data.remote.model.HeroModelRemote
import com.segunfrancis.data.remote.model.ImagesRemote
import com.segunfrancis.data.remote.model.PowerstatsRemote
import com.segunfrancis.data.remote.model.WorkRemote
import com.segunfrancis.domain.model.AppearanceDomain
import com.segunfrancis.domain.model.BiographyDomain
import com.segunfrancis.domain.model.ConnectionsDomain
import com.segunfrancis.domain.model.HeroModelDomain
import com.segunfrancis.domain.model.ImagesDomain
import com.segunfrancis.domain.model.PowerstatsDomain
import com.segunfrancis.domain.model.WorkDomain

private fun WorkRemote.mapToWorkDomain(): WorkDomain {
    return WorkDomain(base, occupation)
}

private fun PowerstatsRemote.mapToPowerStatsDomain(): PowerstatsDomain {
    return PowerstatsDomain(combat, durability, intelligence, power, speed, strength)
}

private fun ImagesRemote.mapToImagesDomain(): ImagesDomain {
    return ImagesDomain(largeImage, mediumImage, smallImage, extraSmallImage)
}

private fun ConnectionsRemote.mapToConnectionsDomain(): ConnectionsDomain {
    return ConnectionsDomain(groupAffiliation, relatives)
}

private fun BiographyRemote.mapToBiographyDomain(): BiographyDomain {
    return BiographyDomain(aliases, firstAppearance, fullName, placeOfBirth, publisher)
}

private fun AppearanceRemote.mapToAppearanceDomain(): AppearanceDomain {
    return AppearanceDomain(eyeColor, gender, hairColor, height, race, weight)
}

fun HeroModelRemote.mapToHeroDomain(): HeroModelDomain {
    return HeroModelDomain(
        id = id,
        name = name,
        appearanceDomain = appearanceRemote.mapToAppearanceDomain(),
        biographyDomain = biographyRemote.mapToBiographyDomain(),
        connectionsDomain = connectionsRemote.mapToConnectionsDomain(),
        imagesDomain = imagesRemote.mapToImagesDomain(),
        powerStats = powerStats.mapToPowerStatsDomain(),
        workDomain = workRemote.mapToWorkDomain()
    )
}
