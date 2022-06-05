package com.example.applesugar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applesugar.db.entity.MarkedMovie
import com.example.applesugar.repositories.MarkedMovieRepository
import kotlinx.coroutines.launch

class MarkedMovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MarkedMovieRepository

    init {
        repository = MarkedMovieRepository(application)
    }

    fun getMarkedMovieByType(type: String): LiveData<List<MarkedMovie>> {
        return repository.getMarkedMovieByType(type)
    }

    fun markMovie(markedMovie: MarkedMovie): MutableLiveData<Long> {
        val rowId = MutableLiveData<Long>()
        viewModelScope.launch {
            rowId.postValue(repository.insertMarkedMovie(markedMovie))
        }
        return rowId
    }

    fun unmarkMovie(id: String): MutableLiveData<Int> {
        val rowId = MutableLiveData<Int>()
        viewModelScope.launch {
            rowId.postValue(repository.deleteMarkedMovie(id))
        }
        return rowId
    }

    fun checkIsMarked(id: String, uid: Int): MutableLiveData<Boolean> {
        val marked = MutableLiveData<Boolean>()
        viewModelScope.launch {
            marked.postValue(repository.checkIsMarked(id, uid))
        }
        return marked
    }

}