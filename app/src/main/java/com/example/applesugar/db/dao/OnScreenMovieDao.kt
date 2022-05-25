package com.example.applesugar.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.applesugar.db.entity.OnScreenMovie

@Dao
interface OnScreenMovieDao {

    @Query("SELECT * FROM movie_on_screen")
    fun getOnScreenMovieList(): LiveData<List<OnScreenMovie>>
}