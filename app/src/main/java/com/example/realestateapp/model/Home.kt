package com.example.realestateapp.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Home(
    @DrawableRes val imageResourceId: Int
) : Parcelable
