package com.jesil.toborowei.hive.superherohive.data.local

import androidx.room.*
import com.segunfrancis.feature.home.model.HeroModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperheroDao {

    @Query("SELECT * FROM superhero_hive_table")
    fun queryAllFavorites(): Flow<List<HeroModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(superheroModels: HeroModel)

    @Delete
    fun removeFavorites(superheroModels: HeroModel)
}