package com.project.fastpost.main.presentation.customer

import android.os.Bundle
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import com.project.fastpost.global.di.addFragmentWithBackStack
import com.project.fastpost.global.di.replaceFragment
import com.project.fastpost.global.utils.LocalStorage
import com.project.fastpost.main.presentation.auth.ChooseRoleFragment
import kotlinx.android.synthetic.main.fragment_profile_customer.*

class ProfileCustomerFragment : BaseFragment() {

    companion object{

        val TAG = "ProfileCustomerFragment"

        fun newInstance(): ProfileCustomerFragment =
            ProfileCustomerFragment()
    }
    override val layoutRes: Int
        get() = R.layout.fragment_profile_customer

    override fun setUp(savedInstanceState: Bundle?) {
        radioBtnCustomer.setOnClickListener {
            radioBtnCustomer.isChecked = true
            radioBtnPostman.isChecked = false
            txtRoleProfile.text = "Отправитель"

        }
        radioBtnPostman.setOnClickListener {
            radioBtnCustomer.isChecked =false
            radioBtnPostman.isChecked = true
            txtRoleProfile.text = "Исполнитель"
        }

        cardViewThree.setOnClickListener { openMyTravelFragment() }
        cardViewFour.setOnClickListener { openMyTravelFragment() }
        txtLogOut.setOnClickListener {
            LocalStorage.setUser(null)
            LocalStorage.setAccessToken(null)
            openChooseRoleFragment()
        }
    }

    private fun openMyTravelFragment(){
        activity?.addFragmentWithBackStack(
            R.id.container,
            MyTravelsFragment.newInstance(),
            MyTravelsFragment.TAG
        )
    }

    private fun openChooseRoleFragment(){
        activity?.replaceFragment(
            R.id.container,
            ChooseRoleFragment.newInstance(),
            ChooseRoleFragment.TAG
        )
    }

}