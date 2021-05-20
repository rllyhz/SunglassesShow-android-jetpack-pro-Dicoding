package id.rllyhz.sunglassesshow.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private var _favMovies: LiveData<PagedList<FavMovie>> = MutableLiveData()
    fun favMovies(): LiveData<PagedList<FavMovie>> = _favMovies

    private var _favTVShows: LiveData<PagedList<FavTVShow>> = MutableLiveData()
    fun favTVShows(): LiveData<PagedList<FavTVShow>> = _favTVShows

    fun deleteFavMovie(favMovie: FavMovie) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFavMovie(favMovie)
    }

    fun deleteFavTVShow(favTVShow: FavTVShow) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteFavTVShow(favTVShow)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            initAllFavMovies()
            initAllFavTVShows()
        }
    }

    suspend fun initAllFavMovies() {
        val result = repository.getFavMovies()
        _favMovies = result

    }

    suspend fun initAllFavTVShows() {
        val result = repository.getFavTVShows()
        _favTVShows = result
    }
}