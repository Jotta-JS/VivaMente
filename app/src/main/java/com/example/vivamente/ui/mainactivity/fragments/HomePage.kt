package com.example.vivamente.ui.mainactivity.fragments

import android.annotation.SuppressLint
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vivamente.R
import com.example.vivamente.applications.MainApplication
import com.example.vivamente.data.local.models.User
import com.example.vivamente.databinding.HomePageBinding
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.MainViewModel
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.MainViewModelFactory

class HomePage : Fragment(R.layout.home_page) {

    private lateinit var mainViewModel : MainViewModel
    private lateinit var binding: HomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainViewModel = ViewModelProvider(this, MainViewModelFactory((this.activity?.application as MainApplication).repository
        )
        )[MainViewModel::class.java]

        binding = HomePageBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val helloUser = mainViewModel.getUserLogged().name.split(" ")
        binding.helloUser.text = "Olá ${helloUser[0]} ${helloUser[1]}"

    }


    companion object{
        fun getInstance(): Fragment{
            return HomePage()
        }
    }

}