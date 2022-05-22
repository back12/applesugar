package com.example.applesugar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applesugar.db.entity.User
import com.example.applesugar.db.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    var userList: LiveData<List<User>>

    init {
        repository = UserRepository(application)
        userList = repository.getUserList()
    }

    fun getUserByName(name: String): LiveData<User> {
        val user = MutableLiveData<User>()
        viewModelScope.launch {
            user.postValue(repository.getUserByName(name))
        }
        return user
    }

}