package com.segunfrancis.data.local.repository

import android.content.SharedPreferences
import com.segunfrancis.data.local.db.SuperheroDao
import com.segunfrancis.data.local.mapper.mapToHeroDomain
import com.segunfrancis.data.local.mapper.mapToHeroLocal
import com.segunfrancis.domain.di.IODispatcher
import com.segunfrancis.domain.di.MainDispatcher
import com.segunfrancis.domain.model.HeroModelDomain
import com.segunfrancis.domain.repository.LocalRepository
import com.segunfrancis.domain.repository.PreferenceRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalRepositoryImpl @Inject constructor(
    private val dao: SuperheroDao,
    private val preferences: SharedPreferences,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : LocalRepository, PreferenceRepository {
    override suspend fun addFavorite(id: Int) {
        withContext(mainDispatcher) { preferences.edit().putBoolean(id.toString(), true) }
    }

    override suspend fun removeFavorite(id: Int) {
        withContext(mainDispatcher) { preferences.edit().putBoolean(id.toString(), false) }
    }

    override suspend fun getFavorite(id: Int): Boolean {
        return withContext(mainDispatcher) { preferences.getBoolean(id.toString(), false) }
    }

    override fun getAllFavorites(): Flow<List<HeroModelDomain>> {
        return dao.queryAllFavorites().map { it.map { favorites -> favorites.mapToHeroDomain() } }
    }

    override suspend fun addFavorite(superHeroModel: HeroModelDomain) {
        withContext(ioDispatcher) { dao.addFavorites(superHeroModel.mapToHeroLocal()) }
    }

    override suspend fun removeFavorites(superHeroModel: HeroModelDomain) {
        withContext(ioDispatcher) { dao.removeFavorites(superHeroModel.mapToHeroLocal()) }
    }
}
