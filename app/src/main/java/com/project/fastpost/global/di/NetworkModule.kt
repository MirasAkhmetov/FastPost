package com.project.fastpost.global.di

import com.project.fastpost.BuildConfig
import com.project.fastpost.global.utils.LocalStorage
import com.project.fastpost.global.service.ServerService
import com.project.fastpost.global.di.ServiceProperties.AUTH_HEADER
import com.project.fastpost.global.di.ServiceProperties.SERVER_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { createOkHttpClient() }
    single { createWebService<ServerService>(get(), SERVER_URL) }
}


object ServiceProperties {
    const val SERVER_URL = "http://194.4.56.241:8889"

    const val AUTH_HEADER = "token"
}

fun createOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    okHttpClientBuilder.connectTimeout(5, TimeUnit.MINUTES)
    okHttpClientBuilder.callTimeout(5, TimeUnit.MINUTES)
    okHttpClientBuilder.readTimeout(5, TimeUnit.MINUTES)
    okHttpClientBuilder.writeTimeout(5, TimeUnit.MINUTES)

    okHttpClientBuilder.addInterceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder()
        request = request.newBuilder()
            .addHeader(AUTH_HEADER, LocalStorage.getAccessToken())
            .url(url.build())
            .build()
        chain.proceed(request)
    }

    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
