package com.segunfrancis.data.remote.repository

import com.segunfrancis.data.remote.api.SuperheroApiService
import com.segunfrancis.data.remote.mapper.mapToHeroDomain
import com.segunfrancis.domain.di.IODispatcher
import com.segunfrancis.domain.model.HeroModelDomain
import com.segunfrancis.domain.repository.RemoteRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteRepositoryImpl @Inject constructor(
    private val api: SuperheroApiService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : RemoteRepository {
    override suspend fun getAllSuperHero(): List<HeroModelDomain> {
        return withContext(dispatcher) {
            api.getSuperheroAllJson().map { hero ->
                hero.mapToHeroDomain()
            }
        }
    }
}
