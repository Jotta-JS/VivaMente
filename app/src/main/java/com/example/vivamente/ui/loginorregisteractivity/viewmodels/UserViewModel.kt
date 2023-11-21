package com.example.vivamente.ui.loginorregisteractivity.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.vivamente.data.MainRepository
import com.example.vivamente.data.local.models.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class UserViewModel(private val repository: MainRepository) : ViewModel() {

    val allUsers : LiveData<List<User>> = repository.allUsers.asLiveData()

    val actualUser: LiveData<List<User>> = repository.actualUser.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }


}

class UserViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}