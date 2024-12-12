package com.harshil.retrofitwithroomkts.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.harshil.retrofitwithroomkts.Model.PostModel
import com.harshil.retrofitwithroomkts.Repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    private val _getAllPostModel = postRepository.getAllPostModel
        .asLiveData(context = viewModelScope.coroutineContext)
    val getAllPostModel: LiveData<List<PostModel>> = _getAllPostModel

    private val _getData = postRepository.getData()
        .catch { e -> Log.e("PostViewModel", "Error fetching data: ${e.message}") }
        .asLiveData()
    val getData: LiveData<List<PostModel>> = _getData

    fun insert(postModelList: List<PostModel>) {
        viewModelScope.launch {
            postRepository.insert(postModelList)
        }
    }
}
