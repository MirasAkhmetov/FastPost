package com.project.fastpost.main.presentation.customer.home

import com.arellomobile.mvp.InjectViewState
import com.project.fastpost.entity.Traveler
import com.project.fastpost.global.di.getErrorMessage
import com.project.fastpost.global.presentation.BasePresenter
import com.project.fastpost.global.presentation.Paginator
import com.project.fastpost.main.data.interactor.CustomerInteractor

@InjectViewState
class HomeCustomerPresenter(
    private val customerInteractor: CustomerInteractor
): BasePresenter<HomeCustomerView>(){
    var page: Int = 1


    fun onFirstInit(){
        paginator.refresh()
    }

    fun onRecomendItemSelected(){
        viewState?.openAgreementCustomerFragment()
    }

    private val paginator = Paginator(
        {
            page = it
            customerInteractor.getTravelers()
        },
        object : Paginator.ViewController<Traveler> {
            override fun showEmptyProgress(show: Boolean) {

            }

            override fun showEmptyError(show: Boolean, error: Throwable?) {

            }

            override fun showEmptyView(show: Boolean) {

            }

            override fun showData(show: Boolean, data: List<Traveler>) {
                viewState?.showTravelers(data)
            }

            override fun showErrorMessage(error: Throwable) {
                viewState?.showMessage(error.getErrorMessage())
            }

            override fun showRefreshProgress(show: Boolean) {

            }

            override fun showPageProgress(show: Boolean) {

            }
        }
    )
}