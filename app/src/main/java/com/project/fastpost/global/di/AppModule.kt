package com.project.fastpost.global.di

import com.project.fastpost.global.di.utilModule
import com.project.fastpost.main.di.interactorAndRepositoryModule
import com.project.fastpost.main.di.mainModule

val appModule = listOf(networkModule, utilModule, interactorAndRepositoryModule, mainModule)