package com.segunfrancis.domain.repository

import com.segunfrancis.domain.model.HeroModelDomain

interface RemoteRepository {
    suspend fun getAllSuperHero() : List<HeroModelDomain>
}