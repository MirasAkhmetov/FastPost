package com.project.fastpost.main.presentation.postman

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment

class MainPostmanFragment: BaseFragment() {

    companion object{

        val TAG = "MainPostmanFragment"

        fun newInstance(): MainPostmanFragment =
            MainPostmanFragment()
    }
    override val layoutRes: Int
    get() = R.layout.fragment_home_customer

    override fun setUp(savedInstanceState: Bundle?) {

    }
}