package com.jesil.toborowei.hive.superherohive.data.repository

import com.jesil.toborowei.hive.superherohive.model.HeroModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getAllFavorites(): Flow<List<HeroModel>>
    suspend fun addFavorite(superHeroModel: HeroModel)
    suspend fun removeFavorites(superHeroModel: HeroModel)
}