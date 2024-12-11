package com.harshil.retrofitwithroomkts.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.harshil.retrofitwithroomkts.Model.Post
import com.harshil.retrofitwithroomkts.Repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val getAllPost: LiveData<List<Post>> = postRepository.getAllPost.flowOn(Dispatchers.Main)
        .asLiveData(context = viewModelScope.coroutineContext)

    fun insert(postList: List<Post>) = viewModelScope.launch {
        postRepository.insert(postList)
    }

    val getData = postRepository.getData().catch { e ->
            Log.d("main", "${e.message}")
        }.asLiveData()

}