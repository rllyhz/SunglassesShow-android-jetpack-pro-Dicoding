package id.rllyhz.sunglassesshow.ui.detail

import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.utils.DataGenerator

class DetailViewModel : ViewModel() {

    fun getSimilarMovieOf(movie: Movie): Array<Movie> =
        DataGenerator.getSimilarMoviesDummyData(movie)

    fun getSimilarTVShowOf(tvShow: TVShow): Array<TVShow> =
        DataGenerator.getSimilarTVShowsDummyData(tvShow)
}