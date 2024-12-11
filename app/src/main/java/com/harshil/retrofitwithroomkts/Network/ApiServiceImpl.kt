package com.harshil.retrofitwithroomkts.Network

import com.harshil.retrofitwithroomkts.Model.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPost():List<Post> = apiService.getAllPost()
}