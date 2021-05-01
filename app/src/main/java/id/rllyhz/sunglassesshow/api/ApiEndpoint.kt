package id.rllyhz.sunglassesshow.api

import id.rllyhz.sunglassesshow.BuildConfig
import id.rllyhz.sunglassesshow.data.source.remote.response.DiscoverMoviesResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.DiscoverTVShowsResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("${API_VERSION}/discover/movie")
    fun discoverMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<DiscoverMoviesResponse>

    @GET("${API_VERSION}/discover/tv")
    fun discoverTVShows(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<DiscoverTVShowsResponse>

    @GET("${API_VERSION}/movie/{movieId}")
    fun getMovieDetailOf(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDetailResponse>

    @GET("${API_VERSION}/tv/{tvShowId}")
    fun getTvShowDetailOf(
        @Path("tvShowId") tvShowId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    )


    companion object {
        private const val API_VERSION = 3
        const val BASE_URL = "https://api.themoviedb.org/"
        const val API_KEY = BuildConfig.API_KEY
    }
}