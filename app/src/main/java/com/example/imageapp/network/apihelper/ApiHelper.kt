package com.example.imageapp.network.apihelper


import com.example.imageapp.CLIENT_ID
import com.example.imageapp.network.apiservice.ApiService
import javax.inject.Inject

/**
 * Created by shande on 09-05-2021
 */
class ApiHelper @Inject constructor(private val apiService: ApiService) {

    suspend fun getImages(page: Int) = apiService.getImages(clientID = CLIENT_ID, page = page)

}