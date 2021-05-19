package id.rllyhz.sunglassesshow.data.source.local

import androidx.paging.DataSource
import androidx.room.*
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import kotlinx.coroutines.flow.Flow

@Dao
interface SunGlassesShowDao {
    @Query("SELECT * FROM tb_fav_movie")
    fun getFavMovies(): DataSource.Factory<Int, FavMovie>

    @Query("SELECT * FROM tb_fav_tvshow")
    fun getFavTVShows(): DataSource.Factory<Int, FavTVShow>

    @Query("SELECT * FROM tb_fav_movie WHERE id = :id")
    suspend fun getFavMovieById(id: Int): FavMovie?

    @Query("SELECT * FROM tb_fav_tvshow WHERE id = :id")
    suspend fun getFavTVShowById(id: Int): FavTVShow?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavMovie(favMovie: FavMovie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavTVShow(favTvShow: FavTVShow): Long

    @Delete
    suspend fun deleteFavMovie(favMovie: FavMovie): Int

    @Delete
    suspend fun deleteFavTVShow(favTvShow: FavTVShow): Int
}