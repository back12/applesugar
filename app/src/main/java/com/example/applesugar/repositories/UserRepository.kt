package com.example.applesugar.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.applesugar.db.AppDatabase
import com.example.applesugar.db.dao.UserDao
import com.example.applesugar.db.entity.User

class UserRepository(context: Context) {
    private val userDao: UserDao
    private val avatarAPI = "https://avatars.dicebear.com/api/open-peeps/"

    init {
        userDao = AppDatabase.getInstance(context).userDao()
    }

    suspend fun updateAvatar(uid: Int, username: String){
        val url = "$avatarAPI$username.svg"
        userDao.updateAvatar(uid, url)
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