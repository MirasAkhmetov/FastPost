package com.project.fastpost.main.presentation.customer.home

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.fastpost.R
import com.project.fastpost.entity.Traveler
import com.project.fastpost.global.base.BaseFragment
import com.project.fastpost.global.di.replaceFragment
import com.project.fastpost.global.di.replaceFragmentWithBackStack
import com.project.fastpost.main.di.MainScope
import com.project.fastpost.main.presentation.common.RecomendCustomerAdapter
import com.project.fastpost.main.presentation.customer.AgreementCustomerFragment
import kotlinx.android.synthetic.main.fragment_home_customer.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class HomeCustomerFragment : BaseFragment(), HomeCustomerView {

    companion object {

        val TAG = "HomeCustomerFragment"

        fun newInstance(): HomeCustomerFragment =
            HomeCustomerFragment()
    }

    @InjectPresenter
    lateinit var presenter: HomeCustomerPresenter

    @ProvidePresenter
    fun providePresenter(): HomeCustomerPresenter {
        getKoin().getScopeOrNull(MainScope.HOME_CUSTOMER_SCOPE)?.close()
        return getKoin().createScope(
            MainScope.HOME_CUSTOMER_SCOPE,
            named(MainScope.HOME_CUSTOMER_SCOPE)
        ).get()
    }

    private val adapter = RecomendCustomerAdapter { presenter.onRecomendItemSelected() }

    override val layoutRes: Int
        get() = R.layout.fragment_home_customer

    override fun setUp(savedInstanceState: Bundle?) {
        presenter.onFirstInit()
        recyclerMainCustomer.adapter = adapter
    }

    override fun showTravelers(dataList: List<Traveler>?) {
        dataList?.let { adapter.submitData(it) }
    }

    override fun openAgreementCustomerFragment() {
        activity?.replaceFragmentWithBackStack(
            R.id.container,
            AgreementCustomerFragment.newInstance(),
            AgreementCustomerFragment.TAG
        )
    }
}