package com.project.fastpost.main.di

import com.project.fastpost.main.presentation.activity.MainActivityPresenter
import com.project.fastpost.main.presentation.auth.sign_in.SignInPresenter
import com.project.fastpost.main.presentation.auth.sign_up.SignUpPresenter
import com.project.fastpost.main.presentation.customer.home.HomeCustomerPresenter
import com.project.fastpost.main.presentation.customer.main.MainCustomerPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {

    scope(named(MainScope.MAIN_ACTIVITY_SCOPE)) {
        scoped { MainActivityPresenter() }
    }

    scope(named(MainScope.MAIN_CUSTOMER_SCOPE)){
        scoped { MainCustomerPresenter() }
    }

    scope(named(MainScope.SIGN_IN_SCOPE)){
        scoped { SignInPresenter(get()) }
    }

    scope(named(MainScope.SIGN_UP_SCOPE)){
        scoped { SignUpPresenter(get(), get()) }
    }
    scope(named(MainScope.HOME_CUSTOMER_SCOPE)){
        scoped { HomeCustomerPresenter(get()) }
    }

}

object MainScope {

    const val MAIN_ACTIVITY_SCOPE = "MainActivityScope"
    const val MAIN_CUSTOMER_SCOPE = "MainCustomerScope"
    const val SIGN_IN_SCOPE = "SignInScope"
    const val SIGN_UP_SCOPE = "SignUpScope"
    const val HOME_CUSTOMER_SCOPE = "HomeCustomerScope"
}