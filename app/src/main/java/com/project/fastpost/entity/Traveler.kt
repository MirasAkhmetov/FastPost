package com.project.fastpost.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Traveler(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("first_name")
    val name: String? = null,
    @SerializedName("last_name")
    val surname: String? = null,
    val from : String? = null,
    val to : String? = null,
    val date : String? = null,
    val price : String? = null,
    val transports : String? = null,
    val avatar: String? = null,
    val weight : Int? = null
):Parcelable

