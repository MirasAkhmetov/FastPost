package com.project.fastpost.global.functional

import android.content.Context
import com.project.fastpost.global.extension.networkInfo

class NetworkHandler (private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}