package com.example.applesugar.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applesugar.db.entity.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment ORDER BY pid desc")
    fun getCommentList(): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComment(comment: Comment): Long
}