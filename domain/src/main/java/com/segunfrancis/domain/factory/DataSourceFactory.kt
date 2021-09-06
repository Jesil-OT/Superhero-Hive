package com.segunfrancis.domain.factory

import com.segunfrancis.domain.repository.LocalRepository
import com.segunfrancis.domain.repository.RemoteRepository
import javax.inject.Inject

class DataSourceFactory @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {
    fun remote() = remoteRepository
    fun local() = localRepository
}
