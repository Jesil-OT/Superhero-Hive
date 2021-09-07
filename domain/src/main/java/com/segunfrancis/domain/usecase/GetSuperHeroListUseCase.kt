package com.segunfrancis.domain.usecase

import com.segunfrancis.domain.di.IODispatcher
import com.segunfrancis.domain.factory.DataSourceFactory
import com.segunfrancis.domain.model.HeroModelDomain
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetSuperHeroListUseCase @Inject constructor(
    private val dataSource: DataSourceFactory,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<HeroModelDomain>> {
        return flow {
            emit(dataSource.remote().getAllSuperHero())
        }.flowOn(dispatcher)
    }
}
