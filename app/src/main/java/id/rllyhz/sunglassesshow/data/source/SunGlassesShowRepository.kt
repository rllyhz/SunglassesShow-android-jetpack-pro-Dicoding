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

    override suspend fun getMovies() =
        remoteDataSource.getMovies()

    override suspend fun getTVShows() =
        remoteDataSource.getTVShows()

    override suspend fun getDetailMovieOf(id: Int) =
        remoteDataSource.getDetailMovieOf(id)

    override suspend fun getDetailTVShowOf(id: Int) =
        remoteDataSource.getDetailTVShowOf(id)

    override suspend fun getSimilarMoviesOf(id: Int) =
        remoteDataSource.getSimilarMoviesOf(id)

    override suspend fun getSimilarTVShowsOf(id: Int) =
        remoteDataSource.getSimilarTVShowsOf(id)
}