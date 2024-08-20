package com.shashank.data.repository

import com.shashank.domain.model.response.RickMortyCharacterDetail
import com.shashank.remote.model.response.RickMortyCharacterDetailResponse

class GetRickMortyCharacterDetailMapper() {

    fun convertDtoRickMortyCharacterDetail(response: RickMortyCharacterDetailResponse): RickMortyCharacterDetail {
        return RickMortyCharacterDetail(
            response.created,
            convertDtoEpisodeList(response.episode),
            response.gender,
            response.id,
            response.image,
            response.name,
            response.species,
            response.status,
            response.type,
            response.url
        )
    }

    private fun convertDtoEpisodeList(episode: List<String?>?): List<String?> {
        val list = mutableListOf<String?>()
        for (item in episode.orEmpty()) {
            list.add(item)
        }
        return list
    }
}