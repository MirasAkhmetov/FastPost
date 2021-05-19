package com.project.fastpost.main.presentation.auth

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import com.project.fastpost.global.di.replaceFragment
import com.project.fastpost.main.presentation.customer.main.MainCustomerFragment
import com.project.fastpost.main.presentation.postman.MainPostmanFragment
import kotlinx.android.synthetic.main.fragment_choose_role.*

class ChooseRoleFragment : BaseFragment() {

    companion object{
        val TAG = "ChooseRoleFragment"

        fun newInstance(): ChooseRoleFragment =
            ChooseRoleFragment()
    }
    override val layoutRes: Int
        get() = R.layout.fragment_choose_role

    override fun setUp(savedInstanceState: Bundle?) {
        btnSendPost.setOnClickListener { openMainCustomerFragment() }
        btnEarnMoney.setOnClickListener { openMainPostmanFragment() }
    }

    private fun openMainCustomerFragment(){
        activity?.replaceFragment(
            R.id.container,
            MainCustomerFragment.newInstance(),
            MainCustomerFragment.TAG
        )
    }

    private fun openMainPostmanFragment(){
        activity?.replaceFragment(
            R.id.container,
            MainCustomerFragment.newInstance(),
            MainCustomerFragment.TAG
        )
    }



}