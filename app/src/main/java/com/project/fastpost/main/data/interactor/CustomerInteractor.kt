package com.project.fastpost.main.data.interactor

import com.project.fastpost.entity.Data
import com.project.fastpost.entity.TravelResponse
import com.project.fastpost.entity.Traveler
import com.project.fastpost.global.system.SchedulersProvider
import com.project.fastpost.main.data.repository.CustomerRepository
import io.reactivex.Single

class CustomerInteractor(
    private val customerRepository: CustomerRepository,
    private val schedulersProvider: SchedulersProvider
) {

    fun getTravelers(): Single<List<Traveler>?> =
        customerRepository.getTravelers().map { it.data?.data }
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())

}