package com.project.fastpost.main.data.repository

import com.project.fastpost.entity.Data
import com.project.fastpost.entity.TravelResponse
import com.project.fastpost.entity.Traveler
import io.reactivex.Single

interface CustomerRepository {

    fun getTravelers(): Single<Data>

}