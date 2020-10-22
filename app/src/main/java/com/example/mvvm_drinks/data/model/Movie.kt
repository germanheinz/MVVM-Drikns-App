package com.example.mvvm_drinks.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val imageMovie : String, val name : String, val description : String): Parcelable {
}