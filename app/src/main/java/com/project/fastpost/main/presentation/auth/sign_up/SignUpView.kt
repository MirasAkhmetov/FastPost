package com.project.fastpost.main.presentation.auth.sign_up

import android.graphics.Bitmap
import com.project.fastpost.global.base.BaseMvpView

interface SignUpView: BaseMvpView {

    fun showDateTitle(title: String)

    fun uploadImageId()

    fun uploadImageIdBack()

    fun openGallery()

    fun openCamera()

    fun showIdentityImage(bitmap: Bitmap)

    fun showIdentityImageBack(bitmap: Bitmap)

   fun openDialogSuccess()
}