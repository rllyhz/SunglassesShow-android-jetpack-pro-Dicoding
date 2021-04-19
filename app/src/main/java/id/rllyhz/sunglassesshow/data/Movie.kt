package id.rllyhz.sunglassesshow.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val posterUrl: String,
    val title: String,
    val year: Int,
    val genres: String,
    val duration: String,
    val rate: String,
    val releasedAt: String,
    val language: String,
    val userScore: String,
    val synopsis: String,
    val tagline: String,
    val director: String
) : Parcelable
