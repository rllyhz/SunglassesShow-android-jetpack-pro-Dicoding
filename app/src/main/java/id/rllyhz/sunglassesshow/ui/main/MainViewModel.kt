package id.rllyhz.sunglassesshow.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {
    private val _firstFetch = MutableLiveData(true)

    private val _movies = MutableLiveData<Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>> = _movies

    private val _tvShows = MutableLiveData<Resource<List<TVShow>>>()
    val tvShows: LiveData<Resource<List<TVShow>>> = _tvShows

    fun initAllMovies() {
        if (_firstFetch.value == true)
            viewModelScope.launch(Dispatchers.Main) {
                _movies.value = getMovies().value
                _firstFetch.value = false
            }
    }

    fun getAllTVShows() {
        if (_firstFetch.value == true)
            viewModelScope.launch(Dispatchers.Main) {
                _tvShows.value = getTVShows().value
                _firstFetch.value = false
            }
    }

    suspend fun getMovies() =
        repository.getMovies()

    suspend fun getTVShows() =
        repository.getTVShows()
}