package com.assessment.insightglobal.domain.repository

import com.assessment.insightglobal.data.model.FlickrResponse
import com.assessment.insightglobal.utils.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotos(tag: String): Flow<ResponseHandler<FlickrResponse>>
}