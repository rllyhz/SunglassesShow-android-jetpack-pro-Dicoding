package id.rllyhz.sunglassesshow.ui.main

import id.rllyhz.sunglassesshow.di.ModuleInjection
import org.junit.Before

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(ModuleInjection.provideMainRepository())
    }
}