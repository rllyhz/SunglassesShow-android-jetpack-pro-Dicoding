package id.rllyhz.sunglassesshow.data.source.local

import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow

open class LocalDataSource constructor(
    private val dao: SunGlassesShowDao
) {
    fun getFavMovies() = dao.getFavMovies()

    fun getFavTVShows() = dao.getFavTVShows()

    suspend fun getFavMovieById(id: Int) = dao.getFavMovieById(id)

    suspend fun getFavTVShowById(id: Int) = dao.getFavTVShowById(id)

    suspend fun addFavMovie(favMovie: FavMovie) = dao.addFavMovie(favMovie)

    suspend fun addFavTVShow(favTVShow: FavTVShow) = dao.addFavTVShow(favTVShow)

    suspend fun deleteFavTVShow(favTVShow: FavTVShow) = dao.deleteFavTVShow(favTVShow)

    suspend fun deleteFavMovie(favMovie: FavMovie) = dao.deleteFavMovie(favMovie)

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(dao: SunGlassesShowDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(dao)
            }
    }
}