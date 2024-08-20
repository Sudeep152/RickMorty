package com.shashank.remote.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RickMortyCharacterDetailResponse(
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
    @Json(name = "name")
    val name: String?,
    @Json(name = "species")
    val species: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)