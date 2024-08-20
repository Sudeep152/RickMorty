package com.shashank.data.repository

import com.shashank.data.mapper.GetRickMortyCharacterListMapper
import com.shashank.domain.common.NetworkResult
import com.shashank.domain.model.response.RickMortyCharacterDetail
import com.shashank.domain.model.response.RickMortyCharacterList
import com.shashank.domain.repository.GetRickMortyRepository
import com.shashank.remote.api.RickMortyApi
import javax.inject.Inject

class GetRickMortyRepositoryImpl @Inject constructor(private val api: RickMortyApi) :
    GetRickMortyRepository {


    private val mapperCharacterList = GetRickMortyCharacterListMapper()
    private val mapperCharacterDetail = GetRickMortyCharacterDetailMapper()


    override suspend fun getRickMortyCharacterList(): NetworkResult<RickMortyCharacterList> {
        return try {
            val response = api.getRickAndMortCharacterList()
            val mappedResponse = mapperCharacterList.convertDtoGetRickMorty(response)
            NetworkResult.Success(mappedResponse)
        } catch (e: Exception) {
            NetworkResult.Error("Error occurred: ${e.message}")
        }
    }

    override suspend fun getRickMortyCharacterDetail(id: Int): NetworkResult<RickMortyCharacterDetail> {
        return try {
            val response = api.getCharacterDetails(id)
            val mappedResponse = mapperCharacterDetail.convertDtoRickMortyCharacterDetail(response)
            NetworkResult.Success(mappedResponse)
        } catch (e: Exception) {
            NetworkResult.Error("Error occurred: ${e.message}")
        }
    }
}