package com.example.applesugar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.applesugar.db.AppDatabase
import com.example.applesugar.db.dao.PostDao
import com.example.applesugar.db.entity.Post

class PostRepository(context: Context) {
    private val postDao: PostDao

    init {
        postDao = AppDatabase.getInstance(context).postDao()
    }

    fun getPostList(): LiveData<List<Post>> {
        return postDao.getPostList()
    }

    suspend fun insertPost(post: Post): Long {
        return postDao.insertPost(post)
    }

    suspend fun updateLiked(liked: Int, pid: Int): Int{
        return postDao.updateLiked(liked, pid)
    }
}