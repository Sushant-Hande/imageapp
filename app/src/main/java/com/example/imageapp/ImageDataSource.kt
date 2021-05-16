package com.example.imageapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imageapp.models.ImageModel
import com.example.imageapp.network.apiservice.ApiService
import kotlinx.coroutines.delay

/**
 * Created by shande on 16-05-2021
 */
class ImageDataSource(private val apiService: ApiService) : PagingSource<Int, ImageModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        return try {

            // This delay is added to simulate the loading effect as with faster internet it is not visible clearly
            delay(3000)
            val nextPageNumber = params.key ?: 0
            val response = apiService.getImages(CLIENT_ID, nextPageNumber)
            LoadResult.Page(
                    data = response,
                    prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                    nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        return state.anchorPosition
    }
}