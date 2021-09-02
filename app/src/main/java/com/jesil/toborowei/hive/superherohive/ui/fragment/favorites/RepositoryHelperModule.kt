package com.jesil.toborowei.hive.superherohive.ui.fragment.favorites

import com.jesil.toborowei.hive.superherohive.data.repository.MainRepository
import com.jesil.toborowei.hive.superherohive.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryHelperModule {

    @Binds
    fun bindRepositoryHelper(repositoryImpl: MainRepositoryImpl): MainRepository
}