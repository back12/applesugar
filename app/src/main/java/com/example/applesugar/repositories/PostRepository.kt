package com.example.applesugar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.applesugar.db.AppDatabase
import com.example.applesugar.db.dao.CommentDao
import com.example.applesugar.db.dao.PostDao
import com.example.applesugar.db.entity.Comment
import com.example.applesugar.db.entity.Post

class PostRepository(context: Context) {
    private val postDao: PostDao
    private val commentDao: CommentDao

    init {
        postDao = AppDatabase.getInstance(context).postDao()
        commentDao = AppDatabase.getInstance(context).commentDao()
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

    fun getCommentList(): LiveData<List<Comment>>{
        return commentDao.getCommentList()
    }

    suspend fun insertComment(comment: Comment): Long{
        return commentDao.insertComment(comment)
    }
}