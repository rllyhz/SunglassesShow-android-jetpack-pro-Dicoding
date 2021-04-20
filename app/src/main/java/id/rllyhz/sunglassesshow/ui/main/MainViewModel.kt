package id.rllyhz.sunglassesshow.ui.main

import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.utils.DataGenerator

class MainViewModel : ViewModel() {

    fun getMovies(): Array<Movie> =
        DataGenerator.getAllMovies()

    fun getTVShows(): Array<TVShow> =
        DataGenerator.getAllTVShows()
}