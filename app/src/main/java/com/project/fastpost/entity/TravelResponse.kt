package com.project.fastpost.entity


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class TravelResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("limit")
    val limit: String? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("pages")
    val pages: Int? = null,
    @SerializedName("data")
    val data: List<Traveler>? = null
):Parcelable