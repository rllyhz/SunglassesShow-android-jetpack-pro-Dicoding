package id.rllyhz.sunglassesshow.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowDetailResponse(
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("name")
    val title: String,
    val tagline: String,
    val status: String,
    val genres: List<GenreResonse>,
    @SerializedName("overview")
    val synopsis: String,
    @SerializedName("first_air_date")
    val releasedDate: String,
    @SerializedName("episode_run_time")
    val duration: List<Int>,
    @SerializedName("original_language")
    val language: String,
    @SerializedName("vote_average")
    val rate: Float
)
