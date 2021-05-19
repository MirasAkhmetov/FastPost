package com.project.fastpost.main.presentation.auth.sign_up

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.messaging.FirebaseMessaging
import com.project.fastpost.entity.User
import com.project.fastpost.global.di.getErrorMessage
import com.project.fastpost.global.di.getFormattedDate
import com.project.fastpost.global.presentation.BasePresenter
import com.project.fastpost.global.utils.AppConstants
import com.project.fastpost.global.utils.LocalStorage
import com.project.fastpost.main.data.interactor.AuthInteractor
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File

@InjectViewState
class SignUpPresenter(
    private val authInteractor: AuthInteractor,
    private val context: Context,

    ) : BasePresenter<SignUpView>(
){

    var dateBirth: String = ""
    var isSender: String = "1"

    private val RC_IDENTITY = 1

    private val RC_IDENTITY_BACK = 2

    private var currentImageRequestCode = RC_IDENTITY

    private val user = User()

    fun onIdentityBtnClicked(){
        currentImageRequestCode = RC_IDENTITY
        viewState?.openCamera()
    }

    fun onIdentityBtnClickedGallery(){
        currentImageRequestCode = RC_IDENTITY
        viewState?.openGallery()
    }

    fun onIdentityBackBtnClicked(){
        currentImageRequestCode = RC_IDENTITY_BACK
        viewState?.openCamera()
    }

    fun onIdentityBackBtnClickedGallery(){
        currentImageRequestCode = RC_IDENTITY_BACK
        viewState?.openGallery()
    }

    fun onSetImagePath(path: String){
        getBitmapFromUri(path)
    }


    fun onSetImage(uri: Uri) {
        getBitmapFromUriPhoto(uri)
    }

    private fun getBitmapFromUri(path: String){
        Glide.with(context)
            .asBitmap()
            .load(File(path))
            .addListener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null){
                        when(currentImageRequestCode){
                            RC_IDENTITY -> {
                                user.identityBitmap = resource
                                viewState?.showIdentityImage(resource)
                            }
                            RC_IDENTITY_BACK -> {
                                user.identityBackBitmap = resource
                                viewState?.showIdentityImageBack(resource)
                            }
                        }
                    }
                    return true
                }
            })
            .submit(250, 250)
    }

    private fun getBitmapFromUriPhoto(uri: Uri){
        Glide.with(context)
            .asBitmap()
            .load(uri)
            .addListener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null){
                        when(currentImageRequestCode){
                            RC_IDENTITY -> {
                                user.identityBitmap = resource
                                viewState?.showIdentityImage(resource)
                            }
                            RC_IDENTITY_BACK -> {
                                user.identityBackBitmap = resource
                                viewState?.showIdentityImageBack(resource)
                            }
                        }
                    }
                    return true
                }
            })
            .submit(250, 250)
    }


    fun onDateSelected(year: Int, month: Int, day: Int){
        dateBirth = "$year-${String.format("%02d", month)}-${String.format("%02d", day)}"
        viewState?.showDateTitle(dateBirth.getFormattedDate())
    }

    fun onNextBtnClicked(phone: String, password: String, surname: String, name: String){
        if (phone.isEmpty() || password.isEmpty() || surname.isEmpty()|| name.isEmpty() || dateBirth!!.isEmpty()){
            viewState?.showMessage("Заполните все поля")
            return
        }

        viewState.showProgressBar(true)
        authInteractor.sendSmsRegister(phone)
            .subscribe(
                {
                    viewState.showMessage(it)
                    viewState?.showProgressBar(false)

                },
                {
                    viewState?.showMessage(it.getErrorMessage())
                    viewState?.showProgressBar(false)
                }
            ).connect()
    }


    fun onRegistrationBtnClicked(
        name: String,
        surname: String,
        phone: String,
        password: String,
        code : String
    ){
        if (phone.isEmpty() || password.isEmpty() || surname.isEmpty()|| name.isEmpty()){
            viewState?.showMessage("Заполните все поля")
            return
        }

        viewState?.showProgressBar(true)
        val params: MutableMap<String, RequestBody> = mutableMapOf()
        params["first_name"] = RequestBody.create(MediaType.parse(AppConstants.MIME_TYPE_TEXT), name)
        params["last_name"] =
            RequestBody.create(MediaType.parse(AppConstants.MIME_TYPE_TEXT), surname)
        params["phone"] = RequestBody.create(MediaType.parse(AppConstants.MIME_TYPE_TEXT), phone)
        params["password"] =
            RequestBody.create(MediaType.parse(AppConstants.MIME_TYPE_TEXT), password)
        params["code"] =
            RequestBody.create(MediaType.parse(AppConstants.MIME_TYPE_TEXT), code)
        params["isSender"] =
            RequestBody.create(MediaType.parse(AppConstants.MIME_TYPE_TEXT), isSender)
        params["birthdate"] =
            RequestBody.create(MediaType.parse(AppConstants.MIME_TYPE_TEXT), dateBirth)

        authInteractor.registration(
            params = params,
            identityBitmap = user.identityBitmap,
            identityBackBitmap = user.identityBackBitmap
        ).subscribe(
            {
                LocalStorage.setUser(it)
                LocalStorage.setAccessToken("Logged In")
                viewState?.openDialogSuccess()
                viewState?.showProgressBar(false)
            },
            {
                viewState?.showMessage(it.getErrorMessage())
                viewState?.showProgressBar(false)
            }
        ).connect()


    }
}