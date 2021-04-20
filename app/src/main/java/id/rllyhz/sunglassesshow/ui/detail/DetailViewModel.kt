package id.rllyhz.sunglassesshow.ui.detail

import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.utils.DataGenerator

class DetailViewModel : ViewModel() {

    fun getSimilarMovie(): Array<Movie> =
        DataGenerator.getSimilarMoviesDummyData()

    fun getSimilarTVShow(): Array<TVShow> =
        DataGenerator.getSimilarTVShowsDummyData()
}