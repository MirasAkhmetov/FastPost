package com.project.fastpost.main.presentation.customer

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_customer_agreement.*

class StepsCustomerFragment: BaseFragment() {

    companion object{

        val TAG = "StepsCustomerFragment"

        fun newInstance(): StepsCustomerFragment =
            StepsCustomerFragment()
    }
    override val layoutRes: Int
        get() = R.layout.fragment_three_steps

    override fun setUp(savedInstanceState: Bundle?) {
        imgBackToolbar.setOnClickListener { activity?.onBackPressed() }
    }

}