package com.project.fastpost.main.presentation.auth.sign_in

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdReceiver
import com.google.firebase.messaging.FirebaseMessaging
import com.project.fastpost.global.di.getErrorMessage
import com.project.fastpost.global.presentation.BasePresenter
import com.project.fastpost.global.utils.LocalStorage
import com.project.fastpost.main.data.interactor.AuthInteractor
import timber.log.Timber

@InjectViewState
class SignInPresenter(
    private val authInteractor: AuthInteractor
): BasePresenter<SignInView>(){



    fun signIn(
        phone: String,
        password: String
    ){
        viewState?.showProgressBar(true)
        authInteractor.signIn(
            phone = phone,
            password = password
        ).subscribe(
            {
                LocalStorage.setAccessToken("Logged In")
                viewState?.openProfileFragment()
                viewState?.showProgressBar(false)
            },
            {
                viewState?.showMessage(it.getErrorMessage())
                viewState?.showProgressBar(false)
            }
        ).connect()
    }
}