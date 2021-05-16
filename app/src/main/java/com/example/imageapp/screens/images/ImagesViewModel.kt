package com.example.imageapp.screens.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.imageapp.ImageDataSource
import com.example.imageapp.network.Resource
import com.example.imageapp.network.apiservice.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by shande on 15-05-2021
 */
@HiltViewModel
class ImagesViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val images = Pager(PagingConfig(10)) {
        ImageDataSource(apiService)
    }.flow.cachedIn(viewModelScope)

}