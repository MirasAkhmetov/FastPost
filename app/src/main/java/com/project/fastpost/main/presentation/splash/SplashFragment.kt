package com.project.fastpost.main.presentation.splash

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment

class SplashFragment: BaseFragment() {

    companion object{
        val TAG = "SplashFragment"

        fun newInstance(): SplashFragment =
            SplashFragment()
    }
    override val layoutRes: Int
        get() = R.layout.fragment_splash

    override fun setUp(savedInstanceState: Bundle?) {
    }
}