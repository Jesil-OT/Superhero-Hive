package com.jesil.toborowei.hive.superherohive.data.repository

import com.jesil.toborowei.hive.superherohive.data.local.SuperheroDao
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.annotation.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: SuperheroDao
) : MainRepository {
    override suspend fun getAllFavorites(): Flow<List<HeroModel>> {
        return dao.queryAllFavorites()
    }

    override suspend fun addFavorite(superHeroModel: HeroModel) {
        dao.addFavorites(superHeroModel)
    }

    override suspend fun removeFavorites(superHeroModel: HeroModel) {
        dao.removeFavorites(superHeroModel)
    }
}