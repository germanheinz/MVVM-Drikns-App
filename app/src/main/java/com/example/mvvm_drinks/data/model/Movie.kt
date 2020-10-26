package com.example.mvvm_drinks.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("poster_path")
    val imageMoviePoster : String,
    @SerializedName("backdrop_path")
    val imageMovie : String,
    @SerializedName("original_title")
    val name : String,
    @SerializedName("overview")
    val description : String
    ): Parcelable {
}

data class MovieList(@SerializedName("results") val movieList: List<Movie>)

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val movieId: String,
    @ColumnInfo(name = "backdrop_path")
    val imageMovie : String,
    @ColumnInfo(name = "original_title")
    val name : String,
    @ColumnInfo(name = "overview")
    val description : String
)