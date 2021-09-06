package com.segunfrancis.data.remote.api

import com.segunfrancis.data.remote.model.HeroModelRemote
import retrofit2.http.GET

interface SuperheroApiService {

    @GET("all.json")
    suspend fun getSuperheroAllJson() : List<HeroModelRemote>

}