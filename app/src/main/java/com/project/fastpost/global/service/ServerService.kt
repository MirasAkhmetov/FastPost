package com.project.fastpost.global.service

import com.project.fastpost.entity.Data
import com.project.fastpost.entity.TravelResponse
import com.project.fastpost.entity.User
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ServerService {

    @FormUrlEncoded
    @POST(Endpoints.PASSWORD_SEND_SMS)
    fun sendSmsRegister(
        @Field("phone") phone: String
    ): Single<String>

    @Multipart
    @POST(Endpoints.REGISTRATION)
    fun registration(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part identityImage: MultipartBody.Part?,
        @Part identityImageBack: MultipartBody.Part?
    ): Single<User>

    @GET(Endpoints.TRAVEL_LIST)
    fun getTravelList(
    ): Single<Data>


    @FormUrlEncoded
    @POST(Endpoints.LOGIN)
    fun signIn(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Completable

}
