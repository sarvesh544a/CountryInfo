package com.kodeco.android.countryinfo.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CountryName(
    val common: String
) : Parcelable
