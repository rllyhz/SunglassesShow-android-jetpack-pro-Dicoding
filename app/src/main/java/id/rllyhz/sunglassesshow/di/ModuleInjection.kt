package id.rllyhz.sunglassesshow.di

import id.rllyhz.sunglassesshow.api.ApiEndpoint
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.data.source.remote.RemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ModuleInjection {

    fun provideApiClient(): ApiEndpoint =
        Retrofit.Builder()
            .baseUrl(ApiEndpoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiEndpoint::class.java)

    fun provideMainRepository(): SunGlassesShowRepository =
        SunGlassesShowRepository.getInstance(
            RemoteDataSource.getInstance()
        )
}