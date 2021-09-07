package com.segunfrancis.feature.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.segunfrancis.common.model.HeroModel
import com.segunfrancis.domain.usecase.GetSuperHeroListUseCase
import com.segunfrancis.feature.home.mapper.mapToHeroHome
import com.segunfrancis.feature.home.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class SuperheroesHiveViewModel @Inject constructor(private val allSuperheroUseCase: GetSuperHeroListUseCase) :
    ViewModel() {

    private val _heroesDataList = MutableLiveData<NetworkResult<List<HeroModel>>>()
    val heroesDataList: LiveData<NetworkResult<List<HeroModel>>> = _heroesDataList

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            _heroesDataList.postValue(NetworkResult.Error(throwable))
            Log.e("SuperherosHiveViewModel", throwable.localizedMessage, throwable)
        }

    init {
        getAllSuperheroes()
    }

    fun getAllSuperheroes() {
        viewModelScope.launch(exceptionHandler) {
            allSuperheroUseCase.invoke()
                .onStart { _heroesDataList.postValue(NetworkResult.Loading) }
                .catch { _heroesDataList.postValue(NetworkResult.Error(it)) }
                .collect { _heroesDataList.postValue(NetworkResult.Success(it.map { superheroes -> superheroes.mapToHeroHome() })) }
        }
    }
}
