package com.project.fastpost.main.data.repository

import com.project.fastpost.entity.Data
import com.project.fastpost.entity.TravelResponse
import com.project.fastpost.entity.Traveler
import com.project.fastpost.global.service.ServerService
import io.reactivex.Single

class CustomerRepositoryImpl(
    private val serverService: ServerService
) : CustomerRepository {

    override fun getTravelers(): Single<Data> =
        serverService.getTravelList()


}