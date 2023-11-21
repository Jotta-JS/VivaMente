package com.example.vivamente.ui.loginorregisteractivity.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vivamente.data.MainRepository
import com.example.vivamente.data.local.models.User

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private var userLogged = User(0,"","","")


    fun setUserLogged(user: User){
        userLogged = user
    }

    fun getUserLogged(): User {
        return userLogged
    }
}

class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
