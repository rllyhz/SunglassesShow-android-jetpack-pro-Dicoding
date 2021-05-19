package id.rllyhz.sunglassesshow.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.utils.Resource

interface SunGlassesShowDataSource {
    suspend fun getMovies(): LiveData<Resource<List<Movie>>>

    suspend fun getTVShows(): LiveData<Resource<List<TVShow>>>

    suspend fun getDetailMovieOf(id: Int): LiveData<Resource<Movie>>

    suspend fun getDetailTVShowOf(id: Int): LiveData<Resource<TVShow>>

    suspend fun getSimilarMoviesOf(id: Int): LiveData<Resource<List<Movie>>>

    suspend fun getSimilarTVShowsOf(id: Int): LiveData<Resource<List<TVShow>>>

    fun getFavMovies(): LiveData<PagedList<FavMovie>>

    fun getFavTVShows(): LiveData<PagedList<FavTVShow>>

    suspend fun getFavMovieById(id: Int): FavMovie?

    suspend fun getFavTVShowById(id: Int): FavTVShow?

    suspend fun addFavMovie(favMovie: FavMovie): Long

    suspend fun addFavTVShow(favTVShow: FavTVShow): Long

    suspend fun deleteFavMovie(favMovie: FavMovie): Int

    suspend fun deleteFavTVShow(favTVShow: FavTVShow): Int
}