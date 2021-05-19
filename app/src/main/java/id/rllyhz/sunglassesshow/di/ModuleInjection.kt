package id.rllyhz.sunglassesshow.di

import android.app.Application
import id.rllyhz.sunglassesshow.api.ApiEndpoint
import id.rllyhz.sunglassesshow.api.ApiEndpoint.Companion.BASE_URL
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.data.source.local.LocalDataSource
import id.rllyhz.sunglassesshow.data.source.local.SunGlassesShowDb
import id.rllyhz.sunglassesshow.data.source.remote.RemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ModuleInjection {
    @Volatile
    private var apiInstance: Retrofit? = null

    private fun provideApiInstance(): Retrofit =
        apiInstance ?: synchronized(this) {
            apiInstance ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    fun provideApiClient(): ApiEndpoint = provideApiInstance()
        .create(ApiEndpoint::class.java)

    fun provideMainRepository(application: Application): SunGlassesShowRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        val db = SunGlassesShowDb.getInstance(application)
        val localDataSource = LocalDataSource.getInstance(db.dao())

        return SunGlassesShowRepository.getInstance(remoteDataSource, localDataSource)
    }
}