package com.harshil.retrofitwithroomkts.Repository

import com.harshil.retrofitwithroomkts.Dao.PostDao
import com.harshil.retrofitwithroomkts.Model.Post
import com.harshil.retrofitwithroomkts.Network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(private val postDao: PostDao, private val apiServiceImpl: ApiServiceImpl) {

    val getAllPost:Flow<List<Post>> = postDao.getAllPost()

    suspend fun insert(postList: List<Post>) = withContext(Dispatchers.IO){
        postDao.insert(postList)
    }

    fun getData():Flow<List<Post>> = flow {
        val response = apiServiceImpl.getAllPost()
        emit(response)
    }.flowOn(Dispatchers.IO)

}