package com.assessment.insightglobal.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlickrResponse(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("generator") val generator: String,
    @SerializedName("items") val items: List<Photo>
) : Parcelable

@Parcelize
data class Photo(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("media") val media: Media,
    @SerializedName("published") val published: String,
    @SerializedName("description") val description: String,
    @SerializedName("author") val author: String,
    @SerializedName("author_id") val authorId: String,
    @SerializedName("tags") val tags: String
) : Parcelable

@Parcelize
data class Media(
    @SerializedName("m") val imageUrl: String
) : Parcelable