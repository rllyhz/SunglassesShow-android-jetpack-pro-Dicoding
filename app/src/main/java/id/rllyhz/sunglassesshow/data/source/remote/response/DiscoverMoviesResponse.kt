package id.rllyhz.sunglassesshow.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    @SerializedName("results")
    val movies: List<MovieResponse>
)