package com.example.vivamente.ui.loginorregisteractivity.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vivamente.MainActivity
import com.example.vivamente.R
import com.example.vivamente.applications.MainApplication
import com.example.vivamente.data.local.models.User
import com.example.vivamente.databinding.FragmentLoginBinding
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.UserViewModel
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.UserViewModelFactory

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModel : UserViewModel
    //private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "logged_user")
    private var users: MutableList<User> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentLoginBinding.inflate(layoutInflater)

        userViewModel = ViewModelProvider(this, UserViewModelFactory(
            (this.activity?.application as MainApplication).repository
        )
        )[UserViewModel::class.java]

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        userViewModel.allUsers.observe(this) {
            users = it.toMutableList()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.fromLoginFragmentToLoginOrRegisterFragment)
        }
        binding.loginAccount.setOnClickListener {
            val email = binding.emailTxtLogin.text.toString()
            val password = binding.passwordTxtLogin.text.toString()

            login(email,password)
        }
    }

    private fun errorInEmail(){
        binding.emailTextField.error = "Usuário não encontrado!"
    }
    private fun errorInPassword(){
        binding.passwordTextField.error = "Senha incorreta!"
    }

    private fun login(email: String, password: String){
        var userExists = false
        var userPassword = ""
        val intent = Intent(Intent(this.activity, MainActivity::class.java))
        for (i in users){
            if (i.email == email){
                userExists = true
                userPassword = i.password
                intent.apply {
                    putExtra("USER_ID", i.id)
                    putExtra("USER_NAME", i.name)
                    putExtra("USER_EMAIL", i.email)
                    putExtra("USER_PASSWORD", i.password)
                }
            }
        }
        if(!userExists){
            errorInEmail()
        }else{
            if (password == userPassword){
                startActivity(intent)
            }else{
                errorInPassword()
            }
        }}
}
