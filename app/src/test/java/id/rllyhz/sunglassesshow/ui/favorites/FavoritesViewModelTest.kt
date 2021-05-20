package id.rllyhz.sunglassesshow.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.utils.CoroutineTestRule
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.PagedListUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {
    @Mock
    private lateinit var repository: SunGlassesShowRepository

    private lateinit var viewModel: FavoritesViewModel

    @Mock
    private lateinit var favMoviesObserver: Observer<PagedList<FavMovie>>

    @Mock
    private lateinit var favTVShowsObserver: Observer<PagedList<FavTVShow>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val favMovies = DataGenerator.getAllFavMovies()
    private val favTVShows = DataGenerator.getAllFavTVShows()

    @Before
    fun setUp() {
        viewModel = FavoritesViewModel(repository)
    }

    @Test
    fun `get all favorite movies and they must not be null`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val favMoviesPagedList = PagedListUtils.mockPagedList(favMovies)
            `when`(favMoviesPagedList.size).thenReturn(6)

            val favMoviesDummyData = MutableLiveData(favMoviesPagedList)

            `when`(repository.getFavMovies()).thenReturn(favMoviesDummyData)

            assertNotNull(viewModel)

            viewModel.initAllFavMovies()
            delay(2000)
            val actualFavMovie = viewModel.favMovies().value
            assertNotNull(actualFavMovie)
            assertEquals(6, actualFavMovie?.size)

            viewModel.favMovies().observeForever(favMoviesObserver)
            verify(favMoviesObserver).onChanged(favMoviesPagedList)
        }

    @Test
    fun `get all favorite tv shows and they must not be null`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val favTVShowsPagedList = PagedListUtils.mockPagedList(favTVShows)
            `when`(favTVShowsPagedList.size).thenReturn(6)

            val favTVShowsDummyData = MutableLiveData(favTVShowsPagedList)

            `when`(repository.getFavTVShows()).thenReturn(favTVShowsDummyData)

            assertNotNull(viewModel)

            viewModel.initAllFavTVShows()
            delay(2000)
            val actualFavTVShow = viewModel.favTVShows().value
            assertNotNull(actualFavTVShow)
            assertEquals(6, actualFavTVShow?.size)

            viewModel.favTVShows().observeForever(favTVShowsObserver)
            verify(favTVShowsObserver).onChanged(favTVShowsPagedList)
        }
}