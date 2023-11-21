package com.example.vivamente.ui.mainactivity.fragments.profileFragment

import androidx.fragment.app.Fragment
import com.example.vivamente.R
import com.example.vivamente.ui.mainactivity.fragments.HomePage

class ProfileFragment: Fragment(R.layout.profile_fragment) {

    companion object{
        fun getInstance(): Fragment{
            return ProfileFragment()
        }
    }

}