package com.shashank.domain.model.response


data class RickMortyCharacterList(
    val info: InfoData?=InfoData(),
    val results: List<ResultData?>?= mutableListOf()
)


data class InfoData(
    val count: Int?=0,
    val next: String?="",
    val pages: Int?=0,
)

data class LocationData(
    val name: String?="",
    val url: String?=""
)

data class OriginData(
    val name: String?="",
    val url: String?=""
)

data class ResultData(
    val created: String?="",
    val episode: List<String?>?= mutableListOf(),
    val gender: String?="",
    val id: Int?=0,
    val image: String?="",
    val location: LocationData?=LocationData(),
    val name: String?="",
    val origin: OriginData?=OriginData(),
    val species: String?="",
    val status: String?="",
    val type: String?="",
    val url: String?=""
)