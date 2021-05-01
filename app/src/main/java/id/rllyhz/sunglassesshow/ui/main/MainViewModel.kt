package id.rllyhz.sunglassesshow.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.ResourceEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {
    private val _state = MutableStateFlow<ResourceEvent>(ResourceEvent.Empty)
    val movieState: StateFlow<ResourceEvent> = _state

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    suspend fun getMoviesTest() =
        repository.getMovies()

    suspend fun getTVShowsTest() =
        repository.getTVShows()

    fun getMovies(): Array<Movie> =
        DataGenerator.getAllMovies()

    fun getTVShows(): Array<TVShow> =
        DataGenerator.getAllTVShows()
}