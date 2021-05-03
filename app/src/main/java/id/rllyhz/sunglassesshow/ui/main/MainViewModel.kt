package id.rllyhz.sunglassesshow.ui.main

import androidx.lifecycle.ViewModel
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository

class MainViewModel(
    private val repository: SunGlassesShowRepository
) : ViewModel() {

    suspend fun getMovies() =
        repository.getMovies()

    suspend fun getTVShows() =
        repository.getTVShows()
}