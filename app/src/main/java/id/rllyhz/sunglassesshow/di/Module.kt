package id.rllyhz.sunglassesshow.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.rllyhz.sunglassesshow.api.ApiEndpoint
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.data.source.remote.RemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideApiInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiEndpoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiClient(retrofitInstance: Retrofit): ApiEndpoint =
        retrofitInstance.create(ApiEndpoint::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource =
        RemoteDataSource.getInstance()

    @Provides
    @Singleton
    fun provideMainRepository(remoteDataSource: RemoteDataSource): SunGlassesShowRepository =
        SunGlassesShowRepository.getInstance(remoteDataSource)
}