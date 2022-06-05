package com.example.applesugar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.applesugar.db.AppDatabase
import com.example.applesugar.db.dao.MarkedMovieDao
import com.example.applesugar.db.entity.MarkedMovie

class MarkedMovieRepository(context: Context) {
    private val markedMovieDao: MarkedMovieDao

    init {
        markedMovieDao = AppDatabase.getInstance(context).markedMovieDao()
    }

    fun getMarkedMovieByType(type: String): LiveData<List<MarkedMovie>> {
        return markedMovieDao.getMarkedMovieByType(type)
    }

    suspend fun insertMarkedMovie(markedMovie: MarkedMovie): Long {
        return markedMovieDao.insertMarkedMovie(markedMovie)
    }

    suspend fun deleteMarkedMovie(id: String): Int {
        return markedMovieDao.deleteMarkedMovie(id)
    }

    suspend fun checkIsMarked(id: String, uid: Int): Boolean {
        return markedMovieDao.checkIsMarked(id, uid) != null
    }
}