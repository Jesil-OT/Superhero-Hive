package com.jesil.toborowei.hive.superherohive.ui.fragment.superheroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.toborowei.hive.superherohive.data.remote.HeroRemoteDataSource
import com.jesil.toborowei.hive.superherohive.data.repository.MainRepository
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperheroesViewModel @Inject constructor(
    private val heroRemoteDataSource: HeroRemoteDataSource,
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _heroDataList: MutableLiveData<DataResult<List<HeroModel>>> =
        MutableLiveData<DataResult<List<HeroModel>>>()
    val heroDataList: LiveData<DataResult<List<HeroModel>>> = _heroDataList

    init {
        loadSuperHeroResult()
    }

    fun loadSuperHeroResult() {
        viewModelScope.launch {
            heroRemoteDataSource.heroList
                .onStart {
                    _heroDataList.postValue(DataResult.Loading)
                }
                .catch {
                    _heroDataList.postValue(DataResult.Error(it))
                }
                .collect {
                    _heroDataList.postValue(DataResult.Success(it))
                }
        }
    }

    fun setFavorites(heroModel: HeroModel) = viewModelScope.launch {
        mainRepository.addFavorite(heroModel)
    }

    fun removeFavorites(heroModel: HeroModel) = viewModelScope.launch {
        mainRepository.removeFavorites(heroModel)
    }
}