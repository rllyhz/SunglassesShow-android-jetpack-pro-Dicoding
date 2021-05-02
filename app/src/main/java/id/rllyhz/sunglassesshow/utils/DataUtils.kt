package id.rllyhz.sunglassesshow.utils

import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.remote.response.GenreResonse
import id.rllyhz.sunglassesshow.data.source.remote.response.MovieDetailResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.MovieResponse
import id.rllyhz.sunglassesshow.data.source.remote.response.TVShowResponse

object DataUtils

fun List<MovieResponse>.asModels(): List<Movie> {
    val allMovies = mutableListOf<Movie>()

    for (movie in this) {
        movie.apply {
            allMovies.add(
                Movie(
                    id,
                    posterPath ?: "",
                    backdropPath ?: "",
                    title,
                    null,
                    null,
                    rate,
                    releasedDate,
                    language,
                    null,
                    synopsis,
                    null
                )
            )
        }
    }

    return allMovies
}

@JvmName("asModelsTVShowResponse")
fun List<TVShowResponse>.asModels(): List<TVShow> {
    val allTvShows = mutableListOf<TVShow>()

    for (tvShow in this) {
        tvShow.apply {
            allTvShows.add(
                TVShow(
                    id,
                    posterPath ?: "",
                    backdropPath ?: "",
                    title,
                    null,
                    null,
                    rate,
                    releasedDate,
                    language,
                    null,
                    synopsis,
                    null
                )
            )
        }
    }

    return allTvShows
}

fun MovieDetailResponse.asModels(): Movie =
    Movie(
        this.id,
        this.posterPath ?: "",
        this.backdropPath ?: "",
        this.title,
        getGenresStringFormat(genres),
        duration,
        rate,
        releasedDate,
        language,
        tagline,
        synopsis,
        status
    )

private fun getGenresStringFormat(genres: List<GenreResonse>): String = run {
    var result = ""

    if (genres.isNotEmpty()) {
        for (genre in genres) {
            if (genres.last() != genre)
                result += "${genre.name}, "
            else
                result += genre.name
        }
    }

    result
}