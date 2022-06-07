package com.example.applesugar.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applesugar.db.entity.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post ORDER BY pid desc")
    fun getPostList(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPost(post: Post): Long

    @Query("UPDATE post SET liked = :liked WHERE pid = :pid")
    suspend fun updateLiked(liked: Int, pid: Int): Int
}