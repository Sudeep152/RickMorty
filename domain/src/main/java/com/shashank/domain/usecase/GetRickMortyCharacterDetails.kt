package com.shashank.domain.usecase

import com.shashank.domain.repository.GetRickMortyRepository
import javax.inject.Inject

class GetRickMortyCharacterDetails @Inject constructor(private val repository: GetRickMortyRepository) {
    suspend operator fun invoke(id: Int) = repository.getRickMortyCharacterDetail(id)
}