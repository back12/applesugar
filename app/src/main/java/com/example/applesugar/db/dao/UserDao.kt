package com.example.applesugar.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.applesugar.db.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUserList(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUserByName(username: String?): User?

    @Insert
    suspend fun insertUser(users: User?)

    @Delete
    suspend fun deleteUser(user: User?)

    @Update
    suspend fun updateUser(user: User?)
}