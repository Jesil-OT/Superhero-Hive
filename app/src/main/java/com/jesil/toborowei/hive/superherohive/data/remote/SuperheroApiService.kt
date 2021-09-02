package com.jesil.toborowei.hive.superherohive.data.remote

import com.jesil.toborowei.hive.superherohive.model.HeroModel
import retrofit2.http.GET

interface SuperheroApiService {
//    base url
//    https://akabab.github.io/superhero-api/api

//    end point
//    /all.json

    @GET("all.json")
    suspend fun getSuperheroAllJson() : List<HeroModel>

}