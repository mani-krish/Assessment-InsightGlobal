package com.assessment.insightglobal.presentation.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.assessment.insightglobal.R
import com.assessment.insightglobal.data.model.Photo
import com.assessment.insightglobal.presentation.theme.UIConstant
import com.assessment.insightglobal.presentation.theme.customBackgroundColor
import com.assessment.insightglobal.presentation.theme.styleAuthor
import com.assessment.insightglobal.presentation.theme.styleDescription
import com.assessment.insightglobal.presentation.theme.styleHeaderDetailScreen
import com.assessment.insightglobal.presentation.theme.stylePublishedDate
import com.assessment.insightglobal.presentation.theme.styleTitle
import com.assessment.insightglobal.utils.Utils

@Composable
fun FlickrPhotoDetail(navController: NavController) {
    val data = remember {
        mutableStateOf(navController.previousBackStackEntry?.savedStateHandle?.get<Photo>("photo"))
    }

    data.value?.let { PhotoDetail(photo = it, navController) }
}

@Composable
fun PhotoDetail(photo: Photo, navController: NavController) {

    Log.d("photo", photo.toString())

    BackHandler(true) {
        navController.popBackStack()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.title_photo_detail),
                color = Color.White,
                style = styleHeaderDetailScreen
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.label_back),
                    tint = Color.White
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = customBackgroundColor))
    }) {
        Column(modifier = Modifier.padding(it)) {
            AsyncImage(
                model = photo.media.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(UIConstant.PhotoHeight)
                    .padding(UIConstant.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(UIConstant.small))

            Text(
                text = photo.title,
                style = styleTitle,
                modifier = Modifier.padding(UIConstant.medium)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(UIConstant.medium)
            ) {
                Text(stringResource(R.string.label_written_by), style = styleAuthor)
                Spacer(modifier = Modifier.width(8.dp)) // Adjust the width as needed
                Text(photo.author)
            }

            Spacer(modifier = Modifier.height(UIConstant.small))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(UIConstant.medium)
            ) {
                Text(stringResource(R.string.label_published_on), style = stylePublishedDate)
                Spacer(modifier = Modifier.width(8.dp)) // Adjust the width as needed
                Text(Utils.formatDate(photo.published))
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                stringResource(R.string.label_description) + " " + photo.description,
                style = styleDescription,
                modifier = Modifier.padding(UIConstant.medium)
            )
        }

    }
}
