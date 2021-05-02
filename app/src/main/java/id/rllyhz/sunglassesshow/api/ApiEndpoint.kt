package id.rllyhz.sunglassesshow.api

import id.rllyhz.sunglassesshow.BuildConfig
import id.rllyhz.sunglassesshow.data.source.remote.response.DiscoverMoviesResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.DiscoverTVShowsResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.MovieDetailResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.TVShowDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("${API_VERSION}/discover/movie")
    suspend fun discoverMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<DiscoverMoviesResponse>

    @GET("${API_VERSION}/discover/tv")
    suspend fun discoverTVShows(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<DiscoverTVShowsResponse>

    @GET("${API_VERSION}/movie/{movieId}")
    suspend fun getMovieDetailOf(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDetailResponse>

    @GET("${API_VERSION}/tv/{tvShowId}")
    suspend fun getTvShowDetailOf(
        @Path("tvShowId") tvShowId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TVShowDetailResponse>

    @GET("${API_VERSION}/movie/{movieId}/similar")
    suspend fun getSimilarMoviesOf(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<DiscoverMoviesResponse>

    @GET("${API_VERSION}/tv/{tvShowId}/similar")
    suspend fun getSimilarTVShowsOf(
        @Path("tvShowId") tvShowId: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): Response<DiscoverTVShowsResponse>


    companion object {
        private const val API_VERSION = 3
        const val BASE_URL = "https://api.themoviedb.org/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
        const val API_KEY = BuildConfig.API_KEY
    }
}