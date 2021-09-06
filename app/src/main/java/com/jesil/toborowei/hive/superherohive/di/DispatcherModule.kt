package com.jesil.toborowei.hive.superherohive.di

import com.jesil.toborowei.hive.superherohive.utils.annotation.IODispatcher
import com.jesil.toborowei.hive.superherohive.utils.annotation.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @IODispatcher
    @Provides
    fun provideIODispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main.immediate
    }
}
