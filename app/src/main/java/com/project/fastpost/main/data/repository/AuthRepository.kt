package com.project.fastpost.main.data.repository

import com.project.fastpost.entity.User
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AuthRepository {


    fun sendSmsRegister(
        phone: String
    ): Single<String>


    fun registration(
        params: Map<String, RequestBody>,
        identityImage: MultipartBody.Part?,
        identityImageBack: MultipartBody.Part?
    ): Single<User>

    fun signIn(
        phone: String,
        password: String
    ): Completable


}