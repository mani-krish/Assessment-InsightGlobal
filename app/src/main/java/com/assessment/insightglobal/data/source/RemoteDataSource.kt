package com.assessment.insightglobal.data.source

import com.assessment.insightglobal.data.api.WebService
import com.assessment.insightglobal.data.model.FlickrResponse
import com.assessment.insightglobal.utils.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/*Call the remote source - API - Photos*/
class RemoteDataSource @Inject constructor(private val api: WebService) {

    fun getCountries(tag: String): Flow<ResponseHandler<FlickrResponse>> =
        flow {
            try {
                emit(ResponseHandler.Loading())
                val response = api.getImages(tag)
                if (response.isSuccessful) {
                    emit(ResponseHandler.Success(response.body()))
                } else {
                    emit(ResponseHandler.Failure(response.message()))
                }
            } catch (e: Exception) {
                emit(ResponseHandler.Failure(e.message.toString()))
            }
        }.flowOn((Dispatchers.IO))
}