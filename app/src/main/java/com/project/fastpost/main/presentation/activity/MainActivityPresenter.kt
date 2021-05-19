package com.project.fastpost.main.presentation.activity

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.project.fastpost.global.presentation.BasePresenter

@InjectViewState
class MainActivityPresenter(
    //  private val authInteractor: AuthInteractor
) : BasePresenter<MainActivityView>() {

    fun onFirstInit() {
        viewState?.openSplashFragment()
        Handler().postDelayed(
            {
                viewState?.openChooseRoleFragment()

            },
            3000
        )
    }


}