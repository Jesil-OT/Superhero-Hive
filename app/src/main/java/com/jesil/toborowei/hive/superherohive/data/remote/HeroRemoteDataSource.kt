package com.jesil.toborowei.hive.superherohive.data.remote

import com.jesil.toborowei.hive.superherohive.model.HeroModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HeroRemoteDataSource @Inject constructor(
    private val apiService: SuperheroApiService
) {
    val heroList: Flow<List<HeroModel>> = flow {
        val heroData = apiService.getSuperheroAllJson()
        emit(heroData)
    }
}