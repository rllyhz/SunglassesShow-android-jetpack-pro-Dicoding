package id.rllyhz.sunglassesshow.utils

import android.util.Log
import com.google.gson.Gson
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.data.source.remote.response.DiscoverMoviesResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.DiscoverTVShowsResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.MovieDetailResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.TVShowDetailResponse

object DataGenerator {
    private val gson = Gson()

    private fun loadAndParseJsonFile(filename: String): String? {
        var jsonString: String? = null

        try {
            val inputStream = javaClass.classLoader?.getResourceAsStream(filename)
            val sizeStream = inputStream?.available()
            val buffer = ByteArray(sizeStream ?: 0)

            inputStream?.read(buffer)
            inputStream?.close()
            jsonString = String(buffer, charset("UTF-8"))
        } catch (exception: Exception) {
            exception.printStackTrace()
            Log.d(javaClass.simpleName, exception.message.toString())
        }

        return jsonString
    }

    fun getAllMovies(): List<Movie> =
        gson.fromJson(
            loadAndParseJsonFile("discoverMovies.json"),
            DiscoverMoviesResponse::class.java
        ).movies.asModels()

    fun getAllTVShows(): List<TVShow> =
        gson.fromJson(
            loadAndParseJsonFile("discoverTVShows.json"),
            DiscoverTVShowsResponse::class.java
        ).tvShows.asModels()

    fun getDetailMovie(): Movie =
        gson.fromJson(loadAndParseJsonFile("detailMovie.json"), MovieDetailResponse::class.java)
            .asModels()

    fun getDetailTvShow(): TVShow =
        gson.fromJson(loadAndParseJsonFile("detailTVShow.json"), TVShowDetailResponse::class.java)
            .asModels()

    fun getSimilarMovies(): List<Movie> =
        gson.fromJson(
            loadAndParseJsonFile("similarMoviesOfMovieId.json"),
            DiscoverMoviesResponse::class.java
        ).movies.asModels()

    fun getSimilarTVShows(): List<TVShow> =
        gson.fromJson(
            loadAndParseJsonFile("similarTVShowsOfTVShowId.json"),
            DiscoverTVShowsResponse::class.java
        ).tvShows.asModels()

}