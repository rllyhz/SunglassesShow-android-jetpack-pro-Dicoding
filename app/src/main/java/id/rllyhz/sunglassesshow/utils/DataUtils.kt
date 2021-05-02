package id.rllyhz.sunglassesshow.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.remote.response.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

fun TVShowDetailResponse.asModels(): TVShow =
    TVShow(
        this.id,
        this.posterPath ?: "",
        this.backdropPath ?: "",
        this.title,
        getGenresStringFormat(genres),
        duration[0],
        rate,
        releasedDate,
        language,
        tagline,
        synopsis,
        status
    )


fun Movie.getDateInString() = getDateInString(releasedAt)
fun TVShow.getDateInString() = getDateInString(releasedAt)

@SuppressLint("SimpleDateFormat")
private fun getDateInString(date: String): String {
    val dateTime = date.split("-")

    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
            val time =
                LocalDateTime.of(
                    dateTime[0].toInt(),
                    dateTime[1].toInt(),
                    dateTime[2].toInt(),
                    0,
                    0
                )
            val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
            time.format(formatter)
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
            val time = Calendar.getInstance().apply {
                set(Calendar.YEAR, dateTime[0].toInt())
                set(Calendar.MONTH, dateTime[1].toInt())
                set(Calendar.DATE, dateTime[2].toInt())
            }.timeInMillis

            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(time))
        }
        else -> date
    }

}

private fun getGenresStringFormat(genres: List<GenreResonse>): String = run {
    var result = ""

    if (genres.isNotEmpty()) {
        for (genre in genres) {
            result += if (genres.last() != genre)
                "${genre.name}, "
            else
                genre.name
        }
    }

    result
}