package com.assessment.insightglobal.data.api

import com.assessment.insightglobal.data.model.FlickrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*Web Service*/
interface WebService {
    @GET("photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getImages(@Query("tags") tags: String): Response<FlickrResponse>
}
