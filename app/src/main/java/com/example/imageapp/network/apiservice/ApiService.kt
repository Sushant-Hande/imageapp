package com.example.imageapp.network.apiservice

import com.example.imageapp.models.ImageModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by shande on 09-05-2021
 */
interface ApiService {

    @GET("photos/")
    suspend fun getImages(@Query(CLIENT_ID) clientID: String, @Query(PAGE) page:Int): List<ImageModel>


    companion object {
        const val CLIENT_ID = "client_id"
        const val PAGE = "page"
    }
}