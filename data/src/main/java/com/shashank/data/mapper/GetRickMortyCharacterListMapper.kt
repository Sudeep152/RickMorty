package com.shashank.data.mapper

import com.shashank.domain.model.response.InfoData
import com.shashank.domain.model.response.LocationData
import com.shashank.domain.model.response.OriginData
import com.shashank.domain.model.response.ResultData
import com.shashank.domain.model.response.RickMortyCharacterList
import com.shashank.remote.model.response.InfoResponse
import com.shashank.remote.model.response.LocationResponse
import com.shashank.remote.model.response.OriginResponse
import com.shashank.remote.model.response.ResultResponse
import com.shashank.remote.model.response.RickMortyCharacterListResponse

class GetRickMortyCharacterListMapper {

    fun convertDtoGetRickMorty(response: RickMortyCharacterListResponse): RickMortyCharacterList {
        return RickMortyCharacterList(
            convertDtoInfoResponse(response.info),
            convertDtoResultListResponse(response.results)
        )
    }

    private fun convertDtoResultListResponse(results: List<ResultResponse?>?): List<ResultData?> {
        val list = mutableListOf<ResultData?>()
        results?.forEach {
            list.add(convertDtoResultData(it))
        }
        return list
    }

    private fun convertDtoResultData(it: ResultResponse?): ResultData {
        return ResultData(
            it?.created,
            it?.episode,
            it?.gender,
            it?.id,
            it?.image,
            convertDtoLocationData(it?.location),
            it?.name,
            convertDtoOriginData(it?.origin),
            it?.species,
            it?.status,
            it?.type,
            it?.url
        )
    }

    private fun convertDtoOriginData(origin: OriginResponse?): OriginData {
        return OriginData(origin?.name, origin?.url)
    }

    private fun convertDtoLocationData(location: LocationResponse?): LocationData {
        return LocationData(location?.name, location?.url)
    }

    private fun convertDtoInfoResponse(info: InfoResponse?): InfoData {
        return InfoData(info?.count, info?.next, info?.pages)
    }
}