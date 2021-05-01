package id.rllyhz.sunglassesshow.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
    val tagline: String,
    val status: String,
    @SerializedName("overview")
    val synopsis: String,
    @SerializedName("release_date")
    val releasedDate: String,
    @SerializedName("runtime")
    val duration: Int,
    @SerializedName("vote_average")
    val rate: Float
)
