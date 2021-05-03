package id.rllyhz.sunglassesshow.ui.detail

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
class DetailViewModelTest {
    @Mock
    private lateinit var repository: SunGlassesShowRepository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<List<Movie>>>

    @Mock
    private lateinit var tvShowsObserver: Observer<Resource<List<TVShow>>>

    @Mock
    private lateinit var detailMovieObserver: Observer<Resource<Movie>>

    @Mock
    private lateinit var detailTVShowObserver: Observer<Resource<TVShow>>

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val itemSizeForTesting = 20
    private val movieIdForTesting = 460465
    private val tvShowIdForTesting = 1416

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun `the active movie must not be null and has the correct properties`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val movieDummyData = DataGenerator.getDetailMovie()
            val movieDummyResource = Resource.Success(movieDummyData)
            val movie = MutableLiveData<Resource<Movie>>()
            movie.value = movieDummyResource

            assertNotNull(movieDummyData)

            `when`(repository.getDetailMovieOf(movieIdForTesting)).thenReturn(movie)
            val actualMovie = viewModel.getDetailMovie(movieDummyData).value?.data
            verify(repository).getDetailMovieOf(movieIdForTesting)
            assertNotNull(actualMovie)
            assertEquals(movieDummyData.id, actualMovie?.id)
            assertEquals(movieDummyData.title, actualMovie?.title)
            assertEquals(movieDummyData.releasedAt, actualMovie?.releasedAt)
            assertEquals(movieDummyData.status, actualMovie?.status)
            assertEquals(movieDummyData.year, actualMovie?.year)
            assertEquals(movieDummyData.duration, actualMovie?.duration)
            assertEquals(movieDummyData.rate, actualMovie?.rate)
            assertEquals(movieDummyData.rating, actualMovie?.rating)

            viewModel.getDetailMovie(movieDummyData).observeForever(detailMovieObserver)
            verify(detailMovieObserver).onChanged(movieDummyResource)
        }

    @Test
    fun `the active tv show must not be null and has the correct properties`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val tvShowDummyData = DataGenerator.getDetailTvShow()
            val tvShowDummyResource = Resource.Success(tvShowDummyData)
            val tvShow = MutableLiveData<Resource<TVShow>>()
            tvShow.value = tvShowDummyResource

            assertNotNull(tvShowDummyData)

            `when`(repository.getDetailTVShowOf(tvShowIdForTesting)).thenReturn(tvShow)
            val actualTVShow = viewModel.getDetailTvShow(tvShowDummyData).value?.data
            verify(repository).getDetailTVShowOf(tvShowIdForTesting)
            assertNotNull(actualTVShow)
            assertEquals(tvShowDummyData.id, actualTVShow?.id)
            assertEquals(tvShowDummyData.title, actualTVShow?.title)
            assertEquals(tvShowDummyData.releasedAt, actualTVShow?.releasedAt)
            assertEquals(tvShowDummyData.status, actualTVShow?.status)
            assertEquals(tvShowDummyData.year, actualTVShow?.year)
            assertEquals(tvShowDummyData.duration, actualTVShow?.duration)
            assertEquals(tvShowDummyData.rate, actualTVShow?.rate)
            assertEquals(tvShowDummyData.rating, actualTVShow?.rating)

            viewModel.getDetailTvShow(tvShowDummyData).observeForever(detailTVShowObserver)
            verify(detailTVShowObserver).onChanged(tvShowDummyResource)
        }

    @Test
    fun `get all similar movie data of active movie, they can not be null and has the correct size`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val activeMovieDummyData = DataGenerator.getDetailMovie()
            val similarMoviesDummyData = DataGenerator.getSimilarMovies()
            val similarMoviesDummyResource = Resource.Success(similarMoviesDummyData)
            val similarMovies = MutableLiveData<Resource<List<Movie>>>()
            similarMovies.value = similarMoviesDummyResource

            `when`(repository.getSimilarMoviesOf(activeMovieDummyData.id)).thenReturn(similarMovies)
            val actualSimilarMovies = viewModel.getSimilarMovieOf(activeMovieDummyData).value?.data
            verify(repository).getSimilarMoviesOf(activeMovieDummyData.id)
            assertNotNull(actualSimilarMovies)
            assertEquals(itemSizeForTesting, actualSimilarMovies?.size)

            viewModel.getSimilarMovieOf(activeMovieDummyData).observeForever(moviesObserver)
            verify(moviesObserver).onChanged(similarMoviesDummyResource)
        }

    @Test
    fun `get all similar tv show data of active tv show, they can not be null and has the correct size`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val activeTvShowDummyData = DataGenerator.getDetailTvShow()
            val similarTVShowsDummyData = DataGenerator.getSimilarTVShows()
            val similarTVShowsDummyResource = Resource.Success(similarTVShowsDummyData)
            val similarTVShows = MutableLiveData<Resource<List<TVShow>>>()
            similarTVShows.value = similarTVShowsDummyResource

            `when`(repository.getSimilarTVShowsOf(activeTvShowDummyData.id)).thenReturn(
                similarTVShows
            )
            val actualSimilarTVShows =
                viewModel.getSimilarTVShowsOf(activeTvShowDummyData).value?.data
            verify(repository).getSimilarTVShowsOf(activeTvShowDummyData.id)
            assertNotNull(actualSimilarTVShows)
            assertEquals(itemSizeForTesting, actualSimilarTVShows?.size)

            viewModel.getSimilarTVShowsOf(activeTvShowDummyData).observeForever(tvShowsObserver)
            verify(tvShowsObserver).onChanged(similarTVShowsDummyResource)
        }
}