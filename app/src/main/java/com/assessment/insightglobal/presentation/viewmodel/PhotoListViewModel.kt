package com.assessment.insightglobal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.insightglobal.R
import com.assessment.insightglobal.data.model.FlickrResponse
import com.assessment.insightglobal.domain.usecase.GetPhotoUseCase
import com.assessment.insightglobal.utils.ResourceProvider
import com.assessment.insightglobal.utils.ResponseHandler
import com.assessment.insightglobal.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val useCase: GetPhotoUseCase, private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _uiItems: MutableStateFlow<ResponseHandler<FlickrResponse>> =
        MutableStateFlow(ResponseHandler.Loading())

    val uiItems: StateFlow<ResponseHandler<FlickrResponse>> = _uiItems

    private val _searchQuery = MutableStateFlow("porcupine")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        searchTextChange("porcupine")
    }

    /*Load the Flickr photos*/
    fun searchTextChange(query: String) {

        _searchQuery.value = query

        viewModelScope.launch {
            try {
                useCase.execute(query).onStart {
                    _uiItems.value = ResponseHandler.Loading()
                }.catch {
                    _uiItems.value = ResponseHandler.Failure(it.message.toString())
                }.collect { it ->
                    when (it.status) {
                        Status.LOADING -> {
                            _uiItems.value = ResponseHandler.Loading()
                        }

                        Status.SUCCESS -> {
                            _uiItems.value = ResponseHandler.Success(it.data)
                            _uiItems.value.let {
                                _uiItems.value = it
                            }
                        }

                        Status.FAILURE -> {
                            _uiItems.value =
                                ResponseHandler.Failure(resourceProvider.getString(R.string.message_no_data))
                        }
                    }
                }
            } catch (e: HttpException) {
                _uiItems.value = ResponseHandler.Failure(
                    e.message ?: resourceProvider.getString(R.string.message_general_error)
                )
            } catch (e: IOException) {
                _uiItems.value =
                    ResponseHandler.Failure(resourceProvider.getString(R.string.message_internet_error))
            } catch (e: Exception) {
                _uiItems.value =
                    ResponseHandler.Failure(resourceProvider.getString(R.string.message_general_error))
            }
        }
    }

}
