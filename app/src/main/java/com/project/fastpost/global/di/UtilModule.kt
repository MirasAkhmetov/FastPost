package com.project.fastpost.global.di

import com.project.fastpost.global.functional.NetworkHandler
import com.project.fastpost.global.system.AppSchedulers
import com.project.fastpost.global.system.ResourceManager
import com.project.fastpost.global.system.SchedulersProvider
import com.project.fastpost.global.utils.ErrorHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val utilModule = module {
    single { AppSchedulers() as SchedulersProvider }
    single { ResourceManager(androidContext()) }
    single { ErrorHandler(get()) }
    single { NetworkHandler(androidContext()) }
}