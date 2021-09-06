package com.jesil.toborowei.hive.superherohive.data.repository

import com.segunfrancis.feature.home.model.HeroModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAllFavorites(): Flow<List<HeroModel>>
    suspend fun addFavorite(superHeroModel: HeroModel)
    suspend fun removeFavorites(superHeroModel: HeroModel)
}