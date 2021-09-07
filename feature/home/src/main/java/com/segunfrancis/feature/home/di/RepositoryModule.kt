package com.segunfrancis.feature.home.di

import com.segunfrancis.data.local.repository.LocalRepositoryImpl
import com.segunfrancis.data.remote.repository.RemoteRepositoryImpl
import com.segunfrancis.domain.repository.LocalRepository
import com.segunfrancis.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepositoryModule {
    
    @Binds
    fun bindRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}
