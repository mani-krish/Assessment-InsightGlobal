package com.assessment.insightglobal.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.assessment.insightglobal.R
import com.assessment.insightglobal.data.model.Photo
import com.assessment.insightglobal.presentation.components.ProgressIndicatorCircular
import com.assessment.insightglobal.presentation.navigation.Destinations
import com.assessment.insightglobal.presentation.theme.UIConstant
import com.assessment.insightglobal.presentation.theme.customBackgroundColor
import com.assessment.insightglobal.presentation.theme.styleError
import com.assessment.insightglobal.presentation.theme.styleHeaderListScreen
import com.assessment.insightglobal.presentation.theme.styleSearch
import com.assessment.insightglobal.presentation.viewmodel.PhotoListViewModel
import com.assessment.insightglobal.utils.ResponseHandler

@Composable
fun FlickrPhotoList(navController: NavController, viewModel: PhotoListViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.title_photo_list),
                color = Color.White,
                style = styleHeaderListScreen
            )
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = customBackgroundColor))
    }) { paddingValues ->
        val searchQuery by viewModel.searchQuery.collectAsState()

        Column(modifier = Modifier.padding(paddingValues)) {

            Spacer(modifier = Modifier.height(UIConstant.SpacerHeight))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::searchTextChange,
                label = { Text(stringResource(R.string.label_search_photos), style = styleSearch) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UIConstant.TextFieldPadding)
            )

            Spacer(modifier = Modifier.height(UIConstant.SpacerHeight))

            PhotoGridView(navController, viewModel)
        }
    }
}

@Composable
fun PhotoGridView(
    navController: NavController, viewModel: PhotoListViewModel = hiltViewModel()
) {
    when (val observableState = viewModel.uiItems.collectAsState().value) {
        is ResponseHandler.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                ProgressIndicatorCircular()
            }
        }

        is ResponseHandler.Success -> {
            observableState.data?.items?.let {
                PhotoItem(photos = it, navController)
            }
        }

        is ResponseHandler.Failure -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = observableState.message.toString(), style = styleError)
            }
        }
    }
}

@Composable
fun PhotoItem(
    photos: List<Photo>, navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        verticalArrangement = Arrangement.spacedBy(UIConstant.GridSpace),
        horizontalArrangement = Arrangement.spacedBy(UIConstant.GridSpace),
        modifier = Modifier
            .fillMaxSize()
            .padding(UIConstant.small)
    ) {
        items(photos) {
            AsyncImage(
                model = it.media.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UIConstant.ImagePadding)
                    .aspectRatio(1f)
                    .clickable {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set("photo", it)
                        }
                        navController.navigate(Destinations.PhotoDetail.route)
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
}