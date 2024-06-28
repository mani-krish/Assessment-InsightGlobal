package com.assessment.insightglobal.presentation.navigation

sealed class Destinations(val route: String) {
    object PhotoList : Destinations("FlickrPhotoList")
    object PhotoDetail : Destinations("FlickrPhotoDetail")
}
