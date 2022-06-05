package com.example.applesugar.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.applesugar.db.entity.MarkedMovie

@Dao
interface MarkedMovieDao {
    @Query("SELECT * FROM marked_movie WHERE type = :type")
    fun getMarkedMovieByType(type: String): LiveData<List<MarkedMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMarkedMovie(markedMovie: MarkedMovie): Long

    @Query("DELETE FROM marked_movie WHERE id = :id")
    suspend fun deleteMarkedMovie(id: String): Int

    @Query("SELECT * FROM marked_movie WHERE id = :id AND uid = :uid")
    suspend fun checkIsMarked(id: String, uid: Int): MarkedMovie
}