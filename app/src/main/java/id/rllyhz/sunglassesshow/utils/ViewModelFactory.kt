package id.rllyhz.sunglassesshow.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.di.ModuleInjection
import id.rllyhz.sunglassesshow.ui.detail.DetailViewModel
import id.rllyhz.sunglassesshow.ui.main.MainViewModel

class ViewModelFactory private constructor(
    private val repository: SunGlassesShowRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(ModuleInjection.provideMainRepository())
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("ViewModel not found: " + modelClass.name)
        }
    }
}