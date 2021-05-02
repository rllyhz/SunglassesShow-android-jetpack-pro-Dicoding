package id.rllyhz.sunglassesshow.data.source

import id.rllyhz.sunglassesshow.data.source.remote.RemoteDataSource

class SunGlassesShowRepository private constructor(
    private val remoteDataSource: RemoteDataSource
) : SunGlassesShowDataSource {

    companion object {
        @Volatile
        private var repoInstance: SunGlassesShowRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): SunGlassesShowRepository =
            repoInstance ?: synchronized(this) {
                repoInstance ?: SunGlassesShowRepository(remoteDataSource)
            }
    }

    suspend fun getMovies() =
        remoteDataSource.getMovies()

    suspend fun getTVShows() =
        remoteDataSource.getTVShows()

    suspend fun getDetailMovieOf(id: Int) =
        remoteDataSource.getDetailMovieOf(id)

    suspend fun getSimilarMoviesOf(id: Int) =
        remoteDataSource.getSimilarMoviesOf(id)

    suspend fun getSimilarTVShowsOf(id: Int) =
        remoteDataSource.getSimilarTVShowsOf(id)
}