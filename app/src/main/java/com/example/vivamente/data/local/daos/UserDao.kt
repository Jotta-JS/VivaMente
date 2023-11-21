package com.example.vivamente.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vivamente.data.local.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getUsers() : Flow<List<User>>

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    fun getActualUser() : Flow<List<User>>

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}