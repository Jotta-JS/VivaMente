package com.example.vivamente.ui.mainactivity.fragments.remindersFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.vivamente.R
import com.example.vivamente.databinding.RemindersFragmentBinding
import com.example.vivamente.ui.mainactivity.fragments.HomePage

class Reminders: Fragment(R.layout.reminders_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val calendar = getView()
    }

    companion object{
        fun getInstance(): Fragment{
            return Reminders()
        }
    }

}