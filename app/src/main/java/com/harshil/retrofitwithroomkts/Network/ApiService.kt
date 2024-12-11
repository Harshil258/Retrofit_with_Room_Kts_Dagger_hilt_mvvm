package com.harshil.retrofitwithroomkts.Network

import com.harshil.retrofitwithroomkts.Model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getAllPost():List<Post>
}