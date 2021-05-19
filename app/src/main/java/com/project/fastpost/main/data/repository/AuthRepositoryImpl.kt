package com.project.fastpost.main.data.repository

import com.project.fastpost.entity.User
import com.project.fastpost.global.service.ServerService
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthRepositoryImpl(
    private val serverService: ServerService
) : AuthRepository {

    override fun sendSmsRegister(phone: String): Single<String> =
        serverService.sendSmsRegister(phone)

    override fun registration(
        params: Map<String, RequestBody>,
        identityImage: MultipartBody.Part?,
        identityImageBack: MultipartBody.Part?
    ): Single<User> =
        serverService.registration(params, identityImage, identityImageBack)

    override fun signIn(phone: String, password: String):Completable =
        serverService.signIn(phone, password)


}