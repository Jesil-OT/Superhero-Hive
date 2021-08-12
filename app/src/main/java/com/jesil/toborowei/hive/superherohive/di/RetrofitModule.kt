package com.jesil.toborowei.hive.superherohive.di

import com.jesil.toborowei.hive.superherohive.data.SuperheroApiService
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideSuperheroApiService(retrofit: Retrofit) : SuperheroApiService =
        retrofit.create(SuperheroApiService::class.java)

}