package com.project.fastpost.main.presentation.customer

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import com.project.fastpost.global.extension.visible
import kotlinx.android.synthetic.main.fragment_customer_agreement.*

class AgreementCustomerFragment : BaseFragment() {

    companion object{

        val TAG = "AgreementCustomerFragment"

        fun newInstance(): AgreementCustomerFragment =
            AgreementCustomerFragment()
    }
    override val layoutRes: Int
        get() = R.layout.fragment_customer_agreement

    override fun setUp(savedInstanceState: Bundle?) {
        imgBackToolbar.setOnClickListener { activity?.onBackPressed() }
        btnPriceSecond.setOnClickListener {
            btnPriceSecond.visible(false)
            btnPriceSecond2.visible(true)
        }
    }
}