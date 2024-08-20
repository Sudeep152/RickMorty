package com.shashank.domain.model.response

data class RickMortyCharacterDetail(
    val created: String? = "",
    val episode: List<String?>?,
    val gender: String? = "",
    val id: Int? = 0,
    val image: String? = "",
    val name: String? = "",
    val species: String? = "",
    val status: String? = "",
    val type: String? = "",
    val url: String? = ""
)