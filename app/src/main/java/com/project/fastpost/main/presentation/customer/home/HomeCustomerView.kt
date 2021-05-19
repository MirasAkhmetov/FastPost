package com.project.fastpost.main.presentation.customer.home

import com.project.fastpost.entity.Data
import com.project.fastpost.entity.TravelResponse
import com.project.fastpost.entity.Traveler
import com.project.fastpost.global.base.BaseMvpView

interface HomeCustomerView : BaseMvpView {

    fun showTravelers(dataList: List<Traveler>?)

    fun openAgreementCustomerFragment()
}