package com.project.fastpost.global.utils

import com.project.fastpost.global.extension.errorMessage
import com.project.fastpost.global.system.ResourceManager

class ErrorHandler (private val resourceManager: ResourceManager) {

    fun proceed(error: Throwable, messageListener: (String) -> Unit = {}) {
        messageListener(error.errorMessage(resourceManager))
    }
}