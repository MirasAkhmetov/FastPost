package com.project.fastpost.main.presentation.customer.main

import com.arellomobile.mvp.InjectViewState
import com.project.fastpost.global.presentation.BasePresenter

@InjectViewState
class MainCustomerPresenter : BasePresenter<MainCustomerView>(){

    fun onFirstInit(){
        viewState?.openHomeCustomerFragment()
    }
}