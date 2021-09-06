package com.jesil.toborowei.hive.superherohive.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.segunfrancis.feature.home.model.HeroModel

@Database(entities = [HeroModel::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class SuperheroDatabase: RoomDatabase() {

    abstract fun superheroDao(): SuperheroDao
}