package com.project.fastpost.app

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.pixplicity.easyprefs.library.Prefs
import com.project.fastpost.global.utils.LocaleManager
import com.project.fastpost.global.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class FastPostApp :Application() {
    companion object{
        var localeManager: LocaleManager? = null
    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@FastPostApp)
            modules(appModule)
        }

        initPrefs()
        initPRDownloader()
        Timber.plant(Timber.DebugTree())
    }

    private fun initPrefs() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    private fun initPRDownloader(){
        val config = PRDownloaderConfig.newBuilder()
            .setReadTimeout(30000)
            .setConnectTimeout(30000)
            .build()
        PRDownloader.initialize(this, config)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager!!.setLocale(this)
    }

    override fun attachBaseContext(base: Context) {
        localeManager = LocaleManager(base)
        super.attachBaseContext(localeManager!!.setLocale(base))
    }
}