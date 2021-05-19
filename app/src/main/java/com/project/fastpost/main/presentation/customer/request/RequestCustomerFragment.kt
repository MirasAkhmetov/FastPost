package com.project.fastpost.main.presentation.customer.request

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment

class RequestCustomerFragment: BaseFragment() {

    companion object{

        val TAG = "RequestCustomerFragment"

        fun newInstance(): RequestCustomerFragment =
            RequestCustomerFragment()
    }
    override val layoutRes: Int
        get() = R.layout.fragment_request_customer

    override fun setUp(savedInstanceState: Bundle?) {

    }
}