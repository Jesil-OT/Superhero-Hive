package com.segunfrancis.data.local.db

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Dao
import com.segunfrancis.data.local.model.HeroModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperheroDao {

    @Query("SELECT * FROM superhero_hive_table")
    fun queryAllFavorites(): Flow<List<HeroModelLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(superheroModels: HeroModelLocal)

    @Delete
    suspend fun removeFavorites(superheroModels: HeroModelLocal)
}
