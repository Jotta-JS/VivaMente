package com.example.vivamente

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.vivamente.applications.MainApplication
import com.example.vivamente.data.local.models.User
import com.example.vivamente.databinding.ActivityMainBinding
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.MainViewModel
import com.example.vivamente.ui.loginorregisteractivity.viewmodels.MainViewModelFactory
import com.example.vivamente.ui.mainactivity.adapters.MainViewPagerAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var mainViewModel : MainViewModel
    private lateinit var adapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory((application as MainApplication).repository
        )
        )[MainViewModel::class.java]

        viewPager2 = _binding.viewPager
        adapter = MainViewPagerAdapter(this)
        viewPager2.adapter = adapter

    }

    override fun onStart() {
        super.onStart()

        val intent = this.intent.extras
        val userLogged: User? = intent?.let {
            User(
                it.getInt("USER_ID"),
                intent.getString("USER_NAME").toString(),
                intent.getString("USER_EMAIL").toString(),
                intent.getString("USER_PASSWORD").toString()
            )
        }
        if (userLogged != null) {
            mainViewModel.setUserLogged(userLogged)
        }

        _binding.navbar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homePage -> {
                    viewPager2.setCurrentItem(0, true)
                    true
                }

                R.id.reminders -> {
                    viewPager2.setCurrentItem(1, true)
                    true
                }

                R.id.profileFragment -> {
                    viewPager2.setCurrentItem(2, true)
                    true
                }

                else -> false
            }
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> _binding.navbar.selectedItemId = R.id.homePage
                    1 -> _binding.navbar.selectedItemId = R.id.reminders
                    2 -> _binding.navbar.selectedItemId = R.id.profileFragment
                }
                super.onPageSelected(position)
            }
        })

    }
}