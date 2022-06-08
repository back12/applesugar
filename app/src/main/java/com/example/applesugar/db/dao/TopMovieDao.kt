package com.example.applesugar.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.applesugar.db.entity.TopMovie

@Dao
interface TopMovieDao {

    @Query("SELECT * FROM movie_top_250")
    fun getTopMovieList(): LiveData<List<TopMovie>>

    @Query("SELECT * FROM movie_top_250 WHERE name LIKE '%' || :name || '%'")
    fun getMovieLikeName(name: String): LiveData<List<TopMovie>>
}