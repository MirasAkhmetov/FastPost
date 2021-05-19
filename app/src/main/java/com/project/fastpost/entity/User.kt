package com.project.fastpost.entity

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Keep
@Parcelize
data class User(
    @SerializedName("avatar")
    var avatar: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("surname")
    var surname: String? = null,
    @SerializedName("name")
    var name: String? = null,
    var identityBitmap: Bitmap? = null,
    var identityBackBitmap: Bitmap? = null
): Parcelable
