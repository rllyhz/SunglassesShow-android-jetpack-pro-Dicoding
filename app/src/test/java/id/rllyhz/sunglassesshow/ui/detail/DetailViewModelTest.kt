package id.rllyhz.sunglassesshow.ui.detail

import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.di.ModuleInjection
import id.rllyhz.sunglassesshow.ui.main.MainViewModel
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var currentMovie: Movie
    private lateinit var currentTVShow: TVShow

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(ModuleInjection.provideMainRepository())
        viewModel = DetailViewModel(ModuleInjection.provideMainRepository())
    }

    @Test
    fun `currentMovie must not be null`() {

    }

    @Test
    fun `currentTVShow must not be null`() {

    }

    @Test
    fun `get all similar movie data, they can not be null and has the correct size`() {
    }

    @Test
    fun `get all similar tv show data, they can not be null and has the correct size`() {
    }
}