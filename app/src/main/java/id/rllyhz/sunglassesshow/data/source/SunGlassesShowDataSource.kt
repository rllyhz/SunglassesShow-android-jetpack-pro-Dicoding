package id.rllyhz.sunglassesshow.data.source

import androidx.lifecycle.LiveData
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.utils.Resource

interface SunGlassesShowDataSource {
    suspend fun getMovies(): LiveData<Resource<List<Movie>>>

    suspend fun getTVShows(): LiveData<Resource<List<TVShow>>>

    suspend fun getDetailMovieOf(id: Int): LiveData<Resource<Movie>>

    suspend fun getDetailTVShowOf(id: Int): LiveData<Resource<TVShow>>

    suspend fun getSimilarMoviesOf(id: Int): LiveData<Resource<List<Movie>>>

    suspend fun getSimilarTVShowsOf(id: Int): LiveData<Resource<List<TVShow>>>
}