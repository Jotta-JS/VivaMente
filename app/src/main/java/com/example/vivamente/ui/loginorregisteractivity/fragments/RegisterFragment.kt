package com.example.vivamente.ui.loginorregisteractivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vivamente.R
import com.example.vivamente.applications.MainApplication
import com.example.vivamente.data.local.models.User
import com.example.vivamente.databinding.FragmentRegisterBinding
import com.example.vivamente.ui.loginorregisteractivity.LoginOrRegisterActivity
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.UserViewModel
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.UserViewModelFactory

class RegisterFragment: Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var userViewModel : UserViewModel
    private var users: MutableList<User> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        userViewModel = ViewModelProvider(this, UserViewModelFactory(
            (this.activity?.application as MainApplication).repository
        )
        )[UserViewModel::class.java]

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        userViewModel.allUsers.observe(this, Observer {
            users = it.toMutableList()
        })

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.fromRegisterFragmentToLoginOrRegisterFragment)
        }

        binding.createAccount.setOnClickListener {
            val name = binding.nameTxtRegister.text.toString()
            val email = binding.emailTxtRegister.text.toString()
            val password = binding.passwordTxtRegitser.toString()
            val confPassword = binding.confirmPasswordTxtRegister.toString()

            if (!email.contains("@") || !email.contains(".")){
                binding.emailTextField.error = "Você não inseriu um email válido"
            }else{
                if (password == confPassword){
                    val user = User( 0, name, email, password)
                    createUser(user)
                    goToLogin()
                }
            }
        }

    }
        private fun createUser(user: User){
        var userExists = false
        for (i in users){
            if (i.email == user.email){
                userExists = true
            }
        }
        if (!userExists) Toast.makeText(this.activity, "Email já cadastrado, tente outro!", Toast.LENGTH_LONG).show()
        else userViewModel.insert(user)
    }

     private fun goToLogin(){
        findNavController().navigate(R.id.fromRegisterFragmentTologinFragment)
    }
}