package com.project.fastpost.main.presentation.customer.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import com.project.fastpost.global.di.replaceFragment
import com.project.fastpost.global.di.replaceFragmentWithBackStack
import com.project.fastpost.global.utils.LocalStorage
import com.project.fastpost.main.di.MainScope
import com.project.fastpost.main.presentation.auth.sign_in.SignInFragment
import com.project.fastpost.main.presentation.customer.ChatCustomerFragment
import com.project.fastpost.main.presentation.customer.home.HomeCustomerFragment
import com.project.fastpost.main.presentation.customer.ProfileCustomerFragment
import com.project.fastpost.main.presentation.customer.request.RequestCustomerFragment
import kotlinx.android.synthetic.main.fragment_main_customer.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class MainCustomerFragment: BaseFragment(), MainCustomerView {

    companion object{

        val TAG = "MainCustomerFragment"

        fun newInstance(): MainCustomerFragment =
            MainCustomerFragment()
    }

    @InjectPresenter
    lateinit var presenter: MainCustomerPresenter


    @ProvidePresenter
    fun providePresenter(): MainCustomerPresenter {
        getKoin().getScopeOrNull(MainScope.MAIN_CUSTOMER_SCOPE)?.close()
        return getKoin().createScope(MainScope.MAIN_CUSTOMER_SCOPE, named(MainScope.MAIN_CUSTOMER_SCOPE)).get()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_main_customer

    override fun setUp(savedInstanceState: Bundle?) {

        presenter.onFirstInit()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home-> openHomeCustomerFragment()
                R.id.nav_request ->{
                    if (LocalStorage.getAccessToken() == LocalStorage.PREF_NO_VAL){
                        openSignInFragment()
                    }else{
                        openRequestCustomerFragment()
                    }
                }
                R.id.nav_chat -> {
                    if (LocalStorage.getAccessToken() == LocalStorage.PREF_NO_VAL){
                        openSignInFragment()
                    }else{
                        openChatCustomerFragment()
                    }

                }
                R.id.nav_profile ->{
                    if (LocalStorage.getAccessToken() == LocalStorage.PREF_NO_VAL){
                        openSignInFragment()
                    }else{
                        openProfileFragment()
                    }
                }
                else -> openHomeCustomerFragment()
            }
            true
        }
        bottom_navigation.itemIconTintList = null
    }


    override fun openHomeCustomerFragment(){
        activity?.replaceFragment(
            R.id.container_main,
            HomeCustomerFragment.newInstance(),
            HomeCustomerFragment.TAG
        )
    }

    private fun openRequestCustomerFragment(){
        activity?.replaceFragment(
            R.id.container_main,
            RequestCustomerFragment.newInstance(),
            RequestCustomerFragment.TAG
        )
    }

    private fun openChatCustomerFragment(){
        activity?.replaceFragment(
            R.id.container_main,
            ChatCustomerFragment.newInstance(),
            ChatCustomerFragment.TAG
        )
    }

    private fun openProfileFragment(){
        activity?.replaceFragment(
            R.id.container_main,
            ProfileCustomerFragment.newInstance(),
            ProfileCustomerFragment.TAG
        )
    }

    private fun openSignInFragment(){
        activity?.replaceFragmentWithBackStack(
            R.id.container,
            SignInFragment.newInstance(),
            SignInFragment.TAG
        )
    }


}