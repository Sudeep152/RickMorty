package com.shashank.physicwallahassignment.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shashank.domain.model.response.ResultData
import com.shashank.physicwallahassignment.viewmodel.GetRickMortyCharacterListViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.RickMortyCharacterListScreen(
    viewModel: GetRickMortyCharacterListViewModel = hiltViewModel(),
    innerPadding: PaddingValues,
    onItemClick: (id: Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val characters = viewModel.rickMortyCharacterList.collectAsState()
    val isLoading = viewModel.loading.collectAsState()
    LaunchedEffect(key1 = true) {
        if (characters.value?.results.isNullOrEmpty()) {
            viewModel.getRickMortyCharacterList()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        LazyColumn {
            items(characters.value?.results?.size ?: 0, key = { it }) {
                CharacterItem(
                    data = characters.value?.results?.get(it) ?: ResultData(),
                    onItemClick,
                    animatedVisibilityScope
                )
            }
        }

        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CharacterItem(
    data: ResultData,
    onItemClick: (id: Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    // Ensure that `data.id` is of type `Int`
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onItemClick(data.id ?: 0) }
            .sharedElement(
                rememberSharedContentState(key = "image/${data.id}"),
                animatedVisibilityScope,
                boundsTransform = { _, _ -> tween(500) }
            ),
        elevation = CardDefaults.elevatedCardElevation(30.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = data.name ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "headline/${data.id}"),
                            animatedVisibilityScope,
                            boundsTransform = { _, _ -> tween(500) }
                        )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = data.species ?: "",
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "species/${data.id}"),
                            animatedVisibilityScope,
                            boundsTransform = { _, _ -> tween(500) }
                        )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = data.gender ?: "",
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "gender/${data.id}"),
                            animatedVisibilityScope,
                            boundsTransform = { _, _ -> tween(500) }
                        )
                )
            }
        }
    }
}