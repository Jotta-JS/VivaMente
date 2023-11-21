package com.example.vivamente.data

import androidx.annotation.WorkerThread
import com.example.vivamente.data.local.daos.UserDao
import com.example.vivamente.data.local.models.User
import kotlinx.coroutines.flow.Flow

class MainRepository(private val userDao: UserDao) {

    val allUsers : Flow<List<User>> = userDao.getUsers()
    val actualUser: Flow<List<User>> = userDao.getActualUser()

    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

}