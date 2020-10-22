package com.example.mvvm_drinks.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("poster_path")
    val imageMovie : String,
    @SerializedName("original_title")
    val name : String,
    @SerializedName("overview")
    val description : String
    ): Parcelable {
}

data class MovieList(@SerializedName("results") val movieList: List<Movie>)