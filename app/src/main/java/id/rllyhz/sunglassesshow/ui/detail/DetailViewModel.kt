package id.rllyhz.sunglassesshow.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.asFavModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {
    private val _firstFetchDetail = MutableLiveData(true)
    private val _firstFetchSimilarContents = MutableLiveData(true)

    private val _detailMovie = MutableLiveData<Resource<Movie>>()
    val detailMovie: LiveData<Resource<Movie>> = _detailMovie

    private val _detailTVShow = MutableLiveData<Resource<TVShow>>()
    val detailTVShow: LiveData<Resource<TVShow>> = _detailTVShow

    private val _similarMovies = MutableLiveData<Resource<List<Movie>>>()
    val similarMovies: LiveData<Resource<List<Movie>>> = _similarMovies

    private val _similarTVShows = MutableLiveData<Resource<List<TVShow>>>()
    val similarTVShows: LiveData<Resource<List<TVShow>>> = _similarTVShows

    private val _isMovieFavorited = MutableLiveData(false)
    val isMovieFavorited: LiveData<Boolean> = _isMovieFavorited

    private val _isTVShowFavorited = MutableLiveData(false)
    val isTVShowFavorited: LiveData<Boolean> = _isTVShowFavorited

    fun initDetailMovie(movie: Movie) {
        if (_firstFetchDetail.value == true)
            viewModelScope.launch(Dispatchers.Main) {
                _detailMovie.value = getDetailMovie(movie).value
                _firstFetchDetail.value = false
            }
    }

    fun initDetailTVShow(tvShow: TVShow) {
        if (_firstFetchDetail.value == true)
            viewModelScope.launch(Dispatchers.Main) {
                _detailTVShow.value = getDetailTvShow(tvShow).value
                _firstFetchDetail.value = false
            }
    }

    fun initSimilarMovies(movie: Movie) {
        if (_firstFetchSimilarContents.value == true)
            viewModelScope.launch(Dispatchers.Main) {
                _similarMovies.value = getSimilarMovieOf(movie).value
                _firstFetchSimilarContents.value = false
            }
    }

    fun initSimilarTVShows(tvShow: TVShow) {
        if (_firstFetchSimilarContents.value == true)
            viewModelScope.launch(Dispatchers.Main) {
                _similarTVShows.value = getSimilarTVShowsOf(tvShow).value
                _firstFetchSimilarContents.value = false
            }
    }

    fun isMovieFavorited(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFavMovieById(movie.id) != null

            withContext(Dispatchers.Main) {
                _isMovieFavorited.value = result
            }
        }
    }

    fun isTVShowFavorited(tvShow: TVShow) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFavTVShowById(tvShow.id) != null

            withContext(Dispatchers.Main) {
                _isTVShowFavorited.value = result
            }
        }
    }

    suspend fun getDetailMovie(movie: Movie) =
        repository.getDetailMovieOf(movie.id)

    suspend fun getDetailTvShow(tvShow: TVShow) =
        repository.getDetailTVShowOf(tvShow.id)

    suspend fun getSimilarMovieOf(movie: Movie) =
        repository.getSimilarMoviesOf(movie.id)

    suspend fun getSimilarTVShowsOf(tvShow: TVShow) =
        repository.getSimilarTVShowsOf(tvShow.id)

    suspend fun addFavMovie(movie: Movie) =
        repository.addFavMovie(movie.asFavModel())

    suspend fun addFavTVShow(tvShow: TVShow) =
        repository.addFavTVShow(tvShow.asFavModel())

    suspend fun deleteFavMovie(movie: Movie) =
        repository.deleteFavMovie(movie.asFavModel())

    suspend fun deleteFavTVShow(tvShow: TVShow) =
        repository.deleteFavTVShow(tvShow.asFavModel())
}