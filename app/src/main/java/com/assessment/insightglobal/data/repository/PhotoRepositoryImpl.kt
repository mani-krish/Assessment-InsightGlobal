package com.assessment.insightglobal.data.repository

import com.assessment.insightglobal.data.model.FlickrResponse
import com.assessment.insightglobal.data.source.RemoteDataSource
import com.assessment.insightglobal.domain.repository.PhotoRepository
import com.assessment.insightglobal.utils.ResponseHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*Repository impl - Decide the remote or local data source*/
class PhotoRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    PhotoRepository {

    override suspend fun getPhotos(tag: String): Flow<ResponseHandler<FlickrResponse>> {
        return remoteDataSource.getCountries(tag)
    }

}