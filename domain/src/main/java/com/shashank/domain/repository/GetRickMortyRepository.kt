package com.shashank.domain.repository

import com.shashank.domain.common.NetworkResult
import com.shashank.domain.model.response.RickMortyCharacterDetail
import com.shashank.domain.model.response.RickMortyCharacterList

interface GetRickMortyRepository {
    suspend fun getRickMortyCharacterList(): NetworkResult<RickMortyCharacterList>
    suspend fun getRickMortyCharacterDetail(id: Int):NetworkResult<RickMortyCharacterDetail>
}