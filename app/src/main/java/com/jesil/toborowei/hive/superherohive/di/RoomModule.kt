package com.jesil.toborowei.hive.superherohive.di

import android.app.Application
import androidx.room.Room
import com.jesil.toborowei.hive.superherohive.data.local.SuperheroDao
import com.jesil.toborowei.hive.superherohive.data.local.SuperheroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideSuperheroDatabase(app : Application): SuperheroDatabase =
        Room.databaseBuilder(app, SuperheroDatabase::class.java, "superhero_hive_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideSuperheroDao(superheroDatabase: SuperheroDatabase): SuperheroDao =
        superheroDatabase.superheroDao()
}