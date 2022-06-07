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

    @Query("UPDATE user SET avatarUrl = :avatarUrl WHERE uid = :uid")
    suspend fun updateAvatar(uid: Int, avatarUrl: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(users: User?): Long

    @Delete
    suspend fun deleteUser(user: User?)

    @Update
    suspend fun updateUser(user: User?)
}