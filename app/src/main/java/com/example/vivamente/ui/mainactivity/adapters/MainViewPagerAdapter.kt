package com.example.vivamente.ui.mainactivity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vivamente.ui.mainactivity.fragments.HomePage
import com.example.vivamente.ui.mainactivity.fragments.profileFragment.ProfileFragment
import com.example.vivamente.ui.mainactivity.fragments.remindersFragment.Reminders

class MainViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    val contents = listOf(
        HomePage.getInstance(),
        Reminders.getInstance(),
        ProfileFragment.getInstance()
    )

    override fun getItemCount(): Int = contents.size

    override fun createFragment(position: Int): Fragment = contents[position]
}