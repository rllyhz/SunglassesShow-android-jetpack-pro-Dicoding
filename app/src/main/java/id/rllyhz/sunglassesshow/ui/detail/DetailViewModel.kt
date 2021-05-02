package id.rllyhz.sunglassesshow.ui.detail

import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.utils.DataGenerator

class DetailViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {

    suspend fun getDetailMovie(movie: Movie) =
        repository.getDetailMovieOf(movie.id)

    suspend fun getDetailTvShow(tvShow: TVShow) {}

    suspend fun getSimilarMovieOf(movie: Movie) =
        repository.getSimilarMoviesOf(movie.id)

    fun getSimilarTVShowOf(tvShow: TVShow): Array<TVShow> =
        DataGenerator.getSimilarTVShowsDummyData(tvShow)
}