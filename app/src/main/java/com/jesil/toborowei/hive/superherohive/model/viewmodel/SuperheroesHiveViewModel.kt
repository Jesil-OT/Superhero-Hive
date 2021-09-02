package com.jesil.toborowei.hive.superherohive.model.viewmodel

import androidx.lifecycle.*
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
class SuperheroesHiveViewModel @Inject constructor(
    private val heroRemoteDataSource: HeroRemoteDataSource,
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _heroDataList: MutableLiveData<DataResult<List<HeroModel>>> =
        MutableLiveData<DataResult<List<HeroModel>>>()
    val heroDataList: LiveData<DataResult<List<HeroModel>>> = _heroDataList

    init {
       loadSuperHeroResult()
    }

    fun loadSuperHeroResult(){
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

    val allFavorites: LiveData<List<HeroModel>> = liveData {
        mainRepository.getAllFavorites().collect { allFavorites ->
            emit(allFavorites)
        }
    }

    fun setFavorites(heroModel: HeroModel){
        mainRepository.addFavorite(heroModel)
    }

    fun removeFavorites(heroModel: HeroModel){
        mainRepository.removeFavorites(heroModel)
    }
}