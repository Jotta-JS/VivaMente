package com.example.vivamente.ui.loginorregisteractivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vivamente.R
import com.example.vivamente.applications.MainApplication
import com.example.vivamente.databinding.FragmentLoginOrRegisterBinding
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.UserViewModel
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.UserViewModelFactory

class LoginOrRegisterFragment: Fragment() {

    private lateinit var binding: FragmentLoginOrRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginOrRegisterBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButtonChoose.setOnClickListener {
            findNavController().navigate(R.id.fromLoginOrRegisterFragmentToLoginFragment)
        }

        binding.registerButtonChoose.setOnClickListener {
            findNavController().navigate(R.id.fromLoginOrRegisterFragmentToRegisterFragment)
        }

    }

    override fun onStart() {
        super.onStart()

    }

}