package com.segunfrancis.feature.home.util

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error(val error: Throwable): NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
