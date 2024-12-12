package com.harshil.retrofitwithroomkts.Network

import com.harshil.retrofitwithroomkts.Model.PostModel
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPost():List<PostModel> = apiService.getAllPost()
}