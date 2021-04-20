package id.rllyhz.sunglassesshow.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    @SerializedName("poster_name")
    val posterName: String,
    val title: String,
    val year: Int,
    val genres: String,
    val duration: String,
    val rate: String,
    @SerializedName("released_at")
    val releasedAt: String,
    val language: String,
    @SerializedName("user_score")
    val userScore: String,
    val tagline: String,
    val synopsis: String,
    val director: String
) : Parcelable
