package com.shashank.physicwallahassignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.domain.common.NetworkResult
import com.shashank.domain.model.response.RickMortyCharacterDetail
import com.shashank.domain.model.response.RickMortyCharacterList
import com.shashank.domain.usecase.GetRickMortyCharacterDetails
import com.shashank.domain.usecase.GetRickMortyCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetRickMortyCharacterListViewModel @Inject constructor(
    private val getRickMortyCharacterListUseCase: GetRickMortyCharacterListUseCase,
) :
    ViewModel() {
    private val _rickMortyCharacterList = MutableStateFlow<RickMortyCharacterList?>(null)
    val rickMortyCharacterList: StateFlow<RickMortyCharacterList?> get() = _rickMortyCharacterList

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun getRickMortyCharacterList() = viewModelScope.launch {
        _loading.value = true
        when (val result = getRickMortyCharacterListUseCase.invoke()) { // Fetch from repository
            is NetworkResult.Success -> {
                _loading.value = false
                _rickMortyCharacterList.value = result.data
            }

            is NetworkResult.Error -> {
                _loading.value = false
                _rickMortyCharacterList.value = null
            }

            is NetworkResult.Loading -> {
                _loading.value = false
                _rickMortyCharacterList.value = null
            }
        }
    }
}