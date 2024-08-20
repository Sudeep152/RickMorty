package com.shashank.physicwallahassignment.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoutes {

    @Serializable
    data object HomeScreen : AppRoutes()

    @Serializable
    data class DetailScreen(val id: Int) : AppRoutes()

}