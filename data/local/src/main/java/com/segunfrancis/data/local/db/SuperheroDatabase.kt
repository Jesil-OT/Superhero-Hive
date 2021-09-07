package com.segunfrancis.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.segunfrancis.data.local.model.HeroModelLocal

@Database(entities = [HeroModelLocal::class], exportSchema = true, version = 1)
@TypeConverters(Converters::class)
abstract class SuperheroDatabase: RoomDatabase() {

    abstract fun superheroDao(): SuperheroDao
}