package com.harshil.retrofitwithroomkts.Repository

import com.harshil.retrofitwithroomkts.Dao.PostDao
import com.harshil.retrofitwithroomkts.Model.PostModel
import com.harshil.retrofitwithroomkts.Network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(private val postDao: PostDao, private val apiServiceImpl: ApiServiceImpl) {

    val getAllPostModel:Flow<List<PostModel>> = postDao.getAllPost()

    suspend fun insert(postModelList: List<PostModel>) = withContext(Dispatchers.IO){
        postDao.insert(postModelList)
    }

    fun getData():Flow<List<PostModel>> = flow {
        val response = apiServiceImpl.getAllPost()
        emit(response)
    }.flowOn(Dispatchers.IO)

}