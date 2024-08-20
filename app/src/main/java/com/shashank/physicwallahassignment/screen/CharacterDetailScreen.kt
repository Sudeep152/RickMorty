package com.shashank.physicwallahassignment.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shashank.physicwallahassignment.R
import com.shashank.physicwallahassignment.viewmodel.GetRickMortyCharacterDetailViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CharacterDetailScreen(
    viewModel: GetRickMortyCharacterDetailViewModel = hiltViewModel(),
    id: Int = 0,
    innerPadding: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    popBackStack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.getRickMortyCharacterDetails(id)
    }
    val characterDetail = viewModel.rickMortyCharacterDetails.collectAsState()
    val isLoading = viewModel.loading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(characterDetail.value?.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()  // Ensure the image takes up the full width available
                    .height(300.dp)  // Set a fixed height for the image
                    .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                    .sharedElement(
                        rememberSharedContentState(key = "image/${characterDetail.value?.id}"),
                        animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(500)
                        }
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 50.dp, start = 15.dp)
                    .clickable {
                        popBackStack()
                    }
            )
        }

        Text(
            text = characterDetail.value?.name ?: "",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .sharedElement(
                    rememberSharedContentState(key = "headline/${characterDetail.value?.id}"),
                    animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(500)
                    }
                )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = characterDetail.value?.species ?: "",
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .sharedElement(
                    rememberSharedContentState(key = "species/${characterDetail.value?.id}"),
                    animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(500)
                    })
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = characterDetail.value?.gender ?: "",
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .sharedElement(
                    rememberSharedContentState(key = "gender/${characterDetail.value?.id}"),
                    animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(500)
                    })
        )

        if (isLoading.value) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }
}