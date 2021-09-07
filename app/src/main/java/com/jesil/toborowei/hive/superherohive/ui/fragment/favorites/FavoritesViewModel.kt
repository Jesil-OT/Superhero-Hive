package com.jesil.toborowei.hive.superherohive.ui.fragment.favorites

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
class FavoritesViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _allFavorites: MutableLiveData<List<HeroModel>> =
        MutableLiveData<List<HeroModel>>()
    val allFavorites: LiveData<List<HeroModel>> = _allFavorites


    init {
        getAllFavorites()
    }

    private fun getAllFavorites() = viewModelScope.launch {
        mainRepository
            .getAllFavorites().collect {
                _allFavorites.value = it
            }
    }

    fun removeFavorites(heroModel: HeroModel) = viewModelScope.launch {
        mainRepository.removeFavorites(heroModel)
    }
}