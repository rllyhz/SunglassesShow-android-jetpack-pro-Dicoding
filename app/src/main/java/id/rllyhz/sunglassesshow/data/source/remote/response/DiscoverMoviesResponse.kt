package id.rllyhz.sunglassesshow.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
)