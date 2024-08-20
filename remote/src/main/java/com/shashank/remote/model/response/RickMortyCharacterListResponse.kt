package com.shashank.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RickMortyCharacterListResponse(
    @Json(name = "info")
    val info: InfoResponse?,
    @Json(name = "results")
    val results: List<ResultResponse?>?
)


@JsonClass(generateAdapter = true)
data class InfoResponse(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "pages")
    val pages: Int?
)

@JsonClass(generateAdapter = true)
data class LocationResponse(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class OriginResponse(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class ResultResponse(
    @Json(name = "created")
    val created: String?,
    @Json(name = "episode")
    val episode: List<String?>?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "location")
    val location: LocationResponse?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "origin")
    val origin: OriginResponse?,
    @Json(name = "species")
    val species: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)