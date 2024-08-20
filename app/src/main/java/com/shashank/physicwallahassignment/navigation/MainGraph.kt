package com.shashank.physicwallahassignment.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shashank.physicwallahassignment.routes.AppRoutes
import com.shashank.physicwallahassignment.screen.CharacterDetailScreen
import com.shashank.physicwallahassignment.screen.RickMortyCharacterListScreen
import com.shashank.physicwallahassignment.viewmodel.GetRickMortyCharacterDetailViewModel
import com.shashank.physicwallahassignment.viewmodel.GetRickMortyCharacterListViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainGraph(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = AppRoutes.HomeScreen) {
            composable<AppRoutes.HomeScreen> {
                val viewModel: GetRickMortyCharacterListViewModel = hiltViewModel()
                RickMortyCharacterListScreen(viewModel, innerPadding, {
                    navController.navigate(AppRoutes.DetailScreen(it))
                }, animatedVisibilityScope = this)
            }
            composable<AppRoutes.DetailScreen> {
                val viewModel: GetRickMortyCharacterDetailViewModel = hiltViewModel()
                val data = it.toRoute<AppRoutes.DetailScreen>()
                CharacterDetailScreen(viewModel, data.id, innerPadding, this) {
                    navController.popBackStack()
                }
            }
        }
    }
}