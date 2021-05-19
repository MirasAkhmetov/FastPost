package com.project.fastpost.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


@Keep
@Parcelize
data class Data(
    val data : TravelResponse? = null
):Parcelable
