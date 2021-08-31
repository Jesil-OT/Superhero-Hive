package com.jesil.toborowei.hive.superherohive.data.local

interface PreferenceHelper {
    fun addFavorite(id: Int)
    fun removeFavorite(id: Int)
    fun getFavorite(id: Int): Boolean
}
