package com.example.vivamente.ui.mainactivity.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class HomePageFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (loadFragmentClass(classLoader, className)) {

            HomePage::class.java -> {

                HomePage()

            }

            else -> {
                super.instantiate(classLoader, className)
            }

        }

    }
}