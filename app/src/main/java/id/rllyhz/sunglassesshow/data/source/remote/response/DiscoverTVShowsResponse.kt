package id.rllyhz.sunglassesshow.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiscoverTVShowsResponse(
    @SerializedName("results")
    val tvShows: List<TVShowResponse>
)