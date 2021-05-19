package com.project.fastpost.main.presentation.auth.sign_in

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.fastpost.R
import com.project.fastpost.global.base.BaseFragment
import com.project.fastpost.global.di.replaceFragment
import com.project.fastpost.global.di.replaceFragmentWithBackStack
import com.project.fastpost.main.di.MainScope
import com.project.fastpost.main.presentation.auth.sign_up.SignUpFragment
import com.project.fastpost.main.presentation.customer.ProfileCustomerFragment
import com.project.fastpost.main.presentation.customer.home.HomeCustomerFragment
import com.project.fastpost.main.presentation.customer.main.MainCustomerFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class SignInFragment : BaseFragment(), SignInView {

    companion object {

        val TAG = "SignInFragment"

        fun newInstance(): SignInFragment =
            SignInFragment()
    }

    @InjectPresenter
    lateinit var presenter: SignInPresenter

    @ProvidePresenter
    fun providePresenter(): SignInPresenter {
        getKoin().getScopeOrNull(MainScope.SIGN_IN_SCOPE)?.close()
        return getKoin().createScope(MainScope.SIGN_IN_SCOPE, named(MainScope.SIGN_IN_SCOPE)).get()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun setUp(savedInstanceState: Bundle?) {
        txtSignUp.setOnClickListener { openSignUpFragment() }
        btnSignIn.setOnClickListener { presenter.signIn("8"+edtPhoneSignIn.unMasked, edtPasswordSignIn.text.toString()) }
    }

    override fun onDestroy() {
        super.onDestroy()
        getKoin().getScopeOrNull(MainScope.SIGN_IN_SCOPE)?.close()
    }

    private fun openSignUpFragment(){
        activity?.replaceFragmentWithBackStack(
            R.id.container,
            SignUpFragment.newInstance(),
            SignUpFragment.TAG
        )
    }

    override fun openProfileFragment() {
        activity?.replaceFragment(
            R.id.container,
            MainCustomerFragment.newInstance(),
            MainCustomerFragment.TAG
        )
    }
}