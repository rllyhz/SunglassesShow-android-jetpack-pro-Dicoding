package id.rllyhz.sunglassesshow.ui.main

import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.utils.DataGenerator

class MainViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {

    suspend fun getMoviesTest() =
        repository.getMovies()

    suspend fun getTVShowsTest() =
        repository.getTVShows()

    fun getMovies(): Array<Movie> =
        DataGenerator.getAllMovies()

    fun getTVShows(): Array<TVShow> =
        DataGenerator.getAllTVShows()
}