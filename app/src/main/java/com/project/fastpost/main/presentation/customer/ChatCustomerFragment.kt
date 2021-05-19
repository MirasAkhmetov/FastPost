package com.project.fastpost.main.presentation.customer

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import com.project.fastpost.global.di.addFragmentWithBackStack
import com.project.fastpost.global.di.replaceFragmentWithBackStack
import kotlinx.android.synthetic.main.fragment_chat_customer.*

class ChatCustomerFragment : BaseFragment() {

    companion object{

        val TAG = "ChatCustomerFragment"

        fun newInstance(): ChatCustomerFragment =
            ChatCustomerFragment()
    }
    override val layoutRes: Int
        get() = R.layout.fragment_chat_customer

    override fun setUp(savedInstanceState: Bundle?) {

        cardView2.setOnClickListener { openAgreementCustomer() }
        cardView3.setOnClickListener { openStepsFragment()  }
    }

    private fun openAgreementCustomer(){
        activity?.addFragmentWithBackStack(
            R.id.container,
            AgreementCustomerFragment.newInstance(),
            AgreementCustomerFragment.TAG
        )
    }

    private fun openStepsFragment(){
        activity?.addFragmentWithBackStack(
            R.id.container,
            StepsCustomerFragment.newInstance(),
            StepsCustomerFragment.TAG
        )
    }
}