package com.shashank.remote.api

import com.shashank.remote.model.response.RickMortyCharacterDetailResponse
import com.shashank.remote.model.response.RickMortyCharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    suspend fun getRickAndMortCharacterList(): RickMortyCharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): RickMortyCharacterDetailResponse
}