package com.shashank.physicwallahassignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.domain.common.NetworkResult
import com.shashank.domain.model.response.RickMortyCharacterDetail
import com.shashank.domain.usecase.GetRickMortyCharacterDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetRickMortyCharacterDetailViewModel @Inject constructor(
    private val getRickMortyCharacterDetails: GetRickMortyCharacterDetails
) :
    ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _rickMortyCharacterDetails = MutableStateFlow<RickMortyCharacterDetail?>(null)
    val rickMortyCharacterDetails: StateFlow<RickMortyCharacterDetail?> get() = _rickMortyCharacterDetails


    fun getRickMortyCharacterDetails(id: Int) = viewModelScope.launch {
        _loading.value = true
        when (val result = getRickMortyCharacterDetails.invoke(id)) { // Fetch from repository
            is NetworkResult.Success -> {
                _loading.value = false
                _rickMortyCharacterDetails.value = result.data
            }

            is NetworkResult.Error -> {
                _loading.value = false
                _rickMortyCharacterDetails.value = null
                Log.e("ErrorAYA", result.message?:"")
            }

            is NetworkResult.Loading -> {
                _loading.value = false
                _rickMortyCharacterDetails.value = null
            }
        }
    }
}