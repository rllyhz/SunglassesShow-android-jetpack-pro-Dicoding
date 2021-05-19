package id.rllyhz.sunglassesshow.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {

    private lateinit var _favMovies: LiveData<PagedList<FavMovie>>
    fun favMovies(): LiveData<PagedList<FavMovie>> = _favMovies

    private lateinit var _favTVShows: LiveData<PagedList<FavTVShow>>
    fun favTVShows(): LiveData<PagedList<FavTVShow>> = _favTVShows

    init {
        initAllFavMovies()
        initAllFavTVShows()
    }

    private fun initAllFavMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFavMovies()
            _favMovies = result
        }
    }

    private fun initAllFavTVShows() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFavTVShows()
            _favTVShows = result
        }
    }
}