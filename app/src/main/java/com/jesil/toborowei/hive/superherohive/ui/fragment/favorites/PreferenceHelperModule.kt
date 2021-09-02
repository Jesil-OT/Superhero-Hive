package com.jesil.toborowei.hive.superherohive.ui.fragment.favorites

import com.jesil.toborowei.hive.superherohive.data.local.PreferenceHelper
import com.jesil.toborowei.hive.superherohive.data.local.PreferenceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PreferenceHelperModule {

    @Binds
    fun bindPreferenceHelper(preferenceHelperImpl: PreferenceHelperImpl): PreferenceHelper
}
