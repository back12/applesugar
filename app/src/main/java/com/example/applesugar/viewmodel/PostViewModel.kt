package com.example.applesugar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applesugar.db.entity.Post
import com.example.applesugar.repositories.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PostRepository
    init {
        repository = PostRepository(application)
    }

    fun getPostList(): LiveData<List<Post>> {
        return repository.getPostList()
    }

    fun insertPost(post: Post): LiveData<Long> {
        val rowId = MutableLiveData<Long>()
        viewModelScope.launch {
            rowId.postValue(repository.insertPost(post))
        }
        return rowId
    }

    fun updateLiked(liked: Int, pid: Int): LiveData<Int>{
        val rowId = MutableLiveData<Int>()
        viewModelScope.launch {
            rowId.postValue(repository.updateLiked(liked, pid))
        }
        return rowId
    }
}