package com.segunfrancis.domain.repository

import com.segunfrancis.domain.model.HeroModelDomain
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllFavorites(): Flow<List<HeroModelDomain>>
    suspend fun addFavorite(superHeroModel: HeroModelDomain)
    suspend fun removeFavorites(superHeroModel: HeroModelDomain)
}
