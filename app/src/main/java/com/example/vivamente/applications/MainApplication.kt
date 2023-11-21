package com.example.vivamente.applications

import android.app.Application
import com.example.vivamente.data.MainRepository
import com.example.vivamente.data.local.MainRoomDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {MainRoomDataBase.getDatabase(scope = applicationScope, context = this)}
    val repository by lazy { MainRepository(database.userDao())}

}