package com.example.applesugar.db.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.applesugar.db.AppDatabase
import com.example.applesugar.db.dao.UserDao
import com.example.applesugar.db.entity.User

class UserRepository(context: Context) {
    private val userDao: UserDao
    init {
        userDao = AppDatabase.getInstance(context).userDao()
    }

    suspend fun updateAvatar(uid: Int, avatarUrl: String){
        userDao.updateAvatar(uid, avatarUrl)
    }

    suspend fun insertUser(user: User): Long{
        return userDao.insertUser(user)
    }

    suspend fun getUserByName(name: String): User?{
        return userDao.getUserByName(name)
    }

    fun getUserList(): LiveData<List<User>>{
        return userDao.getUserList()
    }

}