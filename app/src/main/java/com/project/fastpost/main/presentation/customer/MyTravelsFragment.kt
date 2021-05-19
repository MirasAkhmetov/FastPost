package com.project.fastpost.main.presentation.customer

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_travels.*

class MyTravelsFragment : BaseFragment() {

    companion object{
        val TAG = "MyTravelsFragment"

        fun newInstance(): MyTravelsFragment =
            MyTravelsFragment()
    }



    override val layoutRes: Int
        get() = R.layout.fragment_my_travels

    override fun setUp(savedInstanceState: Bundle?) {

        imgBackToolbar.setOnClickListener { activity?.onBackPressed() }

    }
}