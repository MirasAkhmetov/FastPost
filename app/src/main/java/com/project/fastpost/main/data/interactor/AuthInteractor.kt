package com.project.fastpost.main.data.interactor

import android.graphics.Bitmap
import com.project.fastpost.entity.User
import com.project.fastpost.global.system.SchedulersProvider
import com.project.fastpost.global.utils.MultipartHelper
import com.project.fastpost.main.data.repository.AuthRepository
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.RequestBody

class AuthInteractor(
    private val authRepository: AuthRepository,
    private val scheduler: SchedulersProvider
) {

    fun registration(
        params: Map<String, RequestBody>,
        identityBitmap: Bitmap? = null,
        identityBackBitmap: Bitmap? = null
    ): Single<User> =
        authRepository.registration(
            params,
            MultipartHelper.getBitmapDataFromPath("iid", identityBitmap),
            MultipartHelper.getBitmapDataFromPath("iid_back", identityBackBitmap)
        )
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())

    fun sendSmsRegister(
        phone: String
    ): Single<String> =
        authRepository.sendSmsRegister(phone)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())

    fun signIn(phone: String, password: String): Completable =
        authRepository.signIn(phone, password)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())

}