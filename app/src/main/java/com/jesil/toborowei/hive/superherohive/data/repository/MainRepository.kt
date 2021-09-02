package com.jesil.toborowei.hive.superherohive.data.repository

import com.jesil.toborowei.hive.superherohive.model.HeroModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAllFavorites(): Flow<List<HeroModel>>
    fun addFavorite(superHeroModel: HeroModel)
    fun removeFavorites(superHeroModel: HeroModel)
}