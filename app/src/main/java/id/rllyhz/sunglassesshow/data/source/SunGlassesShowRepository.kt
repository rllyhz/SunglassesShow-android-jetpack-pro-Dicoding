package id.rllyhz.sunglassesshow.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.local.LocalDataSource
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.data.source.remote.RemoteDataSource
import id.rllyhz.sunglassesshow.utils.Resource

class SunGlassesShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : SunGlassesShowDataSource {
    override suspend fun getMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getMovies()

    override suspend fun getTVShows(): LiveData<Resource<List<TVShow>>> =
        remoteDataSource.getTVShows()

    override suspend fun getDetailMovieOf(id: Int): LiveData<Resource<Movie>> =
        remoteDataSource.getDetailMovieOf(id)

    override suspend fun getDetailTVShowOf(id: Int): LiveData<Resource<TVShow>> =
        remoteDataSource.getDetailTVShowOf(id)

    override suspend fun getSimilarMoviesOf(id: Int): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getSimilarMoviesOf(id)

    override suspend fun getSimilarTVShowsOf(id: Int): LiveData<Resource<List<TVShow>>> =
        remoteDataSource.getSimilarTVShowsOf(id)

    override suspend fun getFavMovies(): LiveData<PagedList<FavMovie>> =
        LivePagedListBuilder(localDataSource.getFavMovies(), pagingConfig).build()

    override suspend fun getFavTVShows(): LiveData<PagedList<FavTVShow>> =
        LivePagedListBuilder(localDataSource.getFavTVShows(), pagingConfig).build()

    override suspend fun getFavMovieById(id: Int): FavMovie? =
        localDataSource.getFavMovieById(id)

    override suspend fun getFavTVShowById(id: Int): FavTVShow? =
        localDataSource.getFavTVShowById(id)

    override suspend fun addFavMovie(favMovie: FavMovie) =
        localDataSource.addFavMovie(favMovie)

    override suspend fun addFavTVShow(favTVShow: FavTVShow) =
        localDataSource.addFavTVShow(favTVShow)

    override suspend fun deleteFavMovie(favMovie: FavMovie) =
        localDataSource.deleteFavMovie(favMovie)

    override suspend fun deleteFavTVShow(favTVShow: FavTVShow) =
        localDataSource.deleteFavTVShow(favTVShow)

    private val pagingConfig
        get(): PagedList.Config =
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()


    companion object {
        private var instance: SunGlassesShowRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): SunGlassesShowRepository =
            instance ?: synchronized(this) {
                instance ?: SunGlassesShowRepository(remoteDataSource, localDataSource)
            }
    }
}