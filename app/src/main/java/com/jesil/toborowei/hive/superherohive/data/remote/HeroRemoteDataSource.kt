package com.jesil.toborowei.hive.superherohive.data.remote

import com.segunfrancis.feature.home.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.annotation.IODispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn

class HeroRemoteDataSource @Inject constructor(
    private val apiService: SuperheroApiService,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {
    val heroList: Flow<List<HeroModel>> = flow {
        val heroData = apiService.getSuperheroAllJson()
        emit(heroData)
    }.flowOn(dispatcher)
}