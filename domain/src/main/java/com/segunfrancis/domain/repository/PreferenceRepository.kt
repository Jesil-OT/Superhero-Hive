package com.segunfrancis.domain.repository

interface PreferenceRepository {
    suspend fun addFavorite(id: Int)
    suspend fun removeFavorite(id: Int)
    suspend fun getFavorite(id: Int): Boolean
}