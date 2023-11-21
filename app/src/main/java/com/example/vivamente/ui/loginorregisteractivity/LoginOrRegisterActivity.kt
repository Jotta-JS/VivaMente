package com.example.vivamente.ui.loginorregisteractivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.vivamente.databinding.ActivityLoginOrRegisterBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginOrRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginOrRegisterBinding
   // private val _datastore: DataStore<Preferences> by preferencesDataStore(name = "logged_user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginOrRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {


        }

    }

}
