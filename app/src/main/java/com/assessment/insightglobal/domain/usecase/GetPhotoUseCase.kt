package com.assessment.insightglobal.domain.usecase

import com.assessment.insightglobal.data.model.FlickrResponse
import com.assessment.insightglobal.data.repository.PhotoRepositoryImpl
import com.assessment.insightglobal.utils.ResponseHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*Use-case driven architecture - invoke the repository interface*/
class GetPhotoUseCase @Inject constructor(private val repository: PhotoRepositoryImpl) {
    suspend fun execute(tag: String): Flow<ResponseHandler<FlickrResponse>> {
        return repository.getPhotos(tag)
    }
}