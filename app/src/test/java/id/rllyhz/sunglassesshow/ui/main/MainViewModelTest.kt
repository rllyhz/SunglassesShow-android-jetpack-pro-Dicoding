package id.rllyhz.sunglassesshow.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.utils.CoroutineTestRule
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var repository: SunGlassesShowRepository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<List<Movie>>>

    @Mock
    private lateinit var tvShowsObserver: Observer<Resource<List<TVShow>>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val itemSizeForTesting = 20

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `get all movies, they mus not be null and has the correct size`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val moviesDummyData = DataGenerator.getAllMovies()
            val moviesDummyResource = Resource.Success(moviesDummyData)
            val movies = MutableLiveData<Resource<List<Movie>>>()
            movies.value = moviesDummyResource

            `when`(repository.getMovies()).thenReturn(movies)
            val actualMoviesData = viewModel.getMovies().value?.data
            verify(repository).getMovies()
            assertNotNull(actualMoviesData)
            assertEquals(itemSizeForTesting, actualMoviesData?.size)

            viewModel.getMovies().observeForever(moviesObserver)
            verify(moviesObserver).onChanged(moviesDummyResource)
        }

    @Test
    fun `get all tv shows, they mus not be null and has the correct size`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val tvShowsDummyData = DataGenerator.getAllTVShows()
            val tvShowsDummyResource = Resource.Success(tvShowsDummyData)
            val tvShows = MutableLiveData<Resource<List<TVShow>>>()
            tvShows.value = tvShowsDummyResource

            `when`(repository.getTVShows()).thenReturn(tvShows)
            val actualTVShowsData = viewModel.getTVShows().value?.data
            verify(repository).getTVShows()
            assertNotNull(actualTVShowsData)
            assertEquals(itemSizeForTesting, actualTVShowsData?.size)

            viewModel.getTVShows().observeForever(tvShowsObserver)
            verify(tvShowsObserver).onChanged(tvShowsDummyResource)
        }
}