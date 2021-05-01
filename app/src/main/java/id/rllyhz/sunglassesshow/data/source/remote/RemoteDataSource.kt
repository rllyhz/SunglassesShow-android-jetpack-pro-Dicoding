package id.rllyhz.sunglassesshow.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.rllyhz.sunglassesshow.api.ApiEndpoint
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.di.ModuleInjection
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.asModels

class RemoteDataSource private constructor(
    private val api: ApiEndpoint
) {

    companion object {
        @Volatile
        private var remoteDSInstance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            remoteDSInstance ?: synchronized(this) {
                remoteDSInstance ?: RemoteDataSource(ModuleInjection.provideApiClient())
            }
    }

    suspend fun getMovies(): LiveData<Resource<List<Movie>>> {
        val results = MutableLiveData<Resource<List<Movie>>>(Resource.Empty())

        try {
            results.value = Resource.Loading()

            val response = api.discoverMovies()
            val bodyResponse = response.body()

            if (response.isSuccessful && bodyResponse != null) {
                results.value = Resource.Success(bodyResponse.movies.asModels())
            } else {
                results.value = Resource.Error(response.message())
            }
        } catch (exception: Exception) {
            results.value = Resource.Error("Something went wrong")
        }

        return results
    }

    suspend fun getTVShows(): LiveData<Resource<List<TVShow>>> {
        val results = MutableLiveData<Resource<List<TVShow>>>(Resource.Empty())

        results.value = Resource.Loading()

        val response = api.discoverTVShows()
        val bodyResponse = response.body()

        if (response.isSuccessful && bodyResponse != null) {
            results.value = Resource.Success(bodyResponse.tvShows.asModels())
        } else {
            results.value = Resource.Error(response.message())
        }


        return results
    }
}