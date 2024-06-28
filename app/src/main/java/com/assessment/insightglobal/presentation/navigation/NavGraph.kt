package com.assessment.insightglobal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assessment.insightglobal.presentation.screens.FlickrPhotoDetail
import com.assessment.insightglobal.presentation.screens.FlickrPhotoList

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Destinations.PhotoList.route
    ) {

        composable(route = Destinations.PhotoList.route) {
            FlickrPhotoList(navController)
        }

        composable(route = Destinations.PhotoDetail.route) {
            FlickrPhotoDetail(navController)
        }
    }
}