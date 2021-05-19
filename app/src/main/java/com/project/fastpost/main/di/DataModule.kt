package com.project.fastpost.main.di

import com.project.fastpost.main.data.interactor.AuthInteractor
import com.project.fastpost.main.data.interactor.CustomerInteractor
import com.project.fastpost.main.data.repository.AuthRepository
import com.project.fastpost.main.data.repository.AuthRepositoryImpl
import com.project.fastpost.main.data.repository.CustomerRepository
import com.project.fastpost.main.data.repository.CustomerRepositoryImpl
import org.koin.dsl.module

val interactorAndRepositoryModule = module {

    single<AuthRepository>{ AuthRepositoryImpl(get()) }
    single { AuthInteractor(get(), get()) }


    single<CustomerRepository>{ CustomerRepositoryImpl(get()) }
    single { CustomerInteractor(get(), get()) }



}