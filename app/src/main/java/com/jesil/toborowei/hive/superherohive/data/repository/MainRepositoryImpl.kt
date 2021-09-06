package com.jesil.toborowei.hive.superherohive.data.repository

import com.jesil.toborowei.hive.superherohive.data.local.SuperheroDao
import com.segunfrancis.feature.home.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.annotation.IODispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MainRepositoryImpl @Inject constructor(
    private val dao: SuperheroDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : MainRepository {
    override fun getAllFavorites(): Flow<List<HeroModel>> {
        return dao.queryAllFavorites()
    }

    override suspend fun addFavorite(superHeroModel: HeroModel) {
        withContext(dispatcher) { dao.addFavorites(superHeroModel) }
    }

    override suspend fun removeFavorites(superHeroModel: HeroModel) {
        withContext(dispatcher) { dao.removeFavorites(superHeroModel) }
    }
}
