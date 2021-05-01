package id.rllyhz.sunglassesshow.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiscoverTVShowsResponse(
    val page: Int,
    @SerializedName("results")
    val tvShows: List<TVShowResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
)