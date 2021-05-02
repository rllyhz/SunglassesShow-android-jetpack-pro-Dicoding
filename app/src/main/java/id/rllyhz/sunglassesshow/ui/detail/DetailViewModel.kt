package id.rllyhz.sunglassesshow.ui.detail

import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository

class DetailViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {

    suspend fun getDetailMovie(movie: Movie) =
        repository.getDetailMovieOf(movie.id)

    suspend fun getDetailTvShow(tvShow: TVShow) =
        repository.getDetailTVShowOf(tvShow.id)

    suspend fun getSimilarMovieOf(movie: Movie) =
        repository.getSimilarMoviesOf(movie.id)

    suspend fun getSimilarTVShowsOf(tvShow: TVShow) =
        repository.getSimilarTVShowsOf(tvShow.id)
}