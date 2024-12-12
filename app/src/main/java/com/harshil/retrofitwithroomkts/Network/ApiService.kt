package com.harshil.retrofitwithroomkts.Network

import com.harshil.retrofitwithroomkts.Model.PostModel
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getAllPost():List<PostModel>
}