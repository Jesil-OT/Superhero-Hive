package com.segunfrancis.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.segunfrancis.data.local.model.HeroModel

@Database(entities = [HeroModel::class], exportSchema = true, version = 1)
@TypeConverters(Converters::class)
abstract class SuperheroDatabase: RoomDatabase() {

    abstract fun superheroDao(): SuperheroDao
}