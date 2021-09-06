package com.segunfrancis.data.remote

import com.segunfrancis.data.remote.model.HeroModel
import retrofit2.http.GET

interface SuperheroApiService {

    @GET("all.json")
    suspend fun getSuperheroAllJson() : List<HeroModel>

}