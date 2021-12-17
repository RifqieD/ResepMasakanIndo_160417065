package com.example.resepmasakanindo_160417065.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.resepmasakanindo_160417065.model.User

@Dao
interface UserDao {
    @Query("SELECT userId, username FROM user WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User

    @Query("SELECT userId, username FROM user WHERE userId = :userId")
    suspend fun findUser(userId: Long): User

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: User): Long
}