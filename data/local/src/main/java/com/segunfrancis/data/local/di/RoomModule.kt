package com.segunfrancis.data.local.di

import android.app.Application
import androidx.room.Room
import com.segunfrancis.data.local.db.SuperheroDao
import com.segunfrancis.data.local.db.SuperheroDatabase
import com.segunfrancis.data.local.util.LocalConstants.DATABASE_NAME
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
        Room.databaseBuilder(app, SuperheroDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideSuperheroDao(superheroDatabase: SuperheroDatabase): SuperheroDao =
        superheroDatabase.superheroDao()
}
