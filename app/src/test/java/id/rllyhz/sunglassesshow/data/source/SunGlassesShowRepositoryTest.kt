package id.rllyhz.sunglassesshow.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
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
class SunGlassesShowRepositoryTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

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

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val itemSizeForTesting = 20
    private val movieIdForTesting = 460465
    private val tvShowIdForTesting = 1416

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `fetch movies list, they must be not null and has expected sizes`() = runBlocking {
        val moviesDummyData = DataGenerator.getAllMovies()
        val moviesDummyResource = Resource.Success(moviesDummyData)
        val movies = MutableLiveData<Resource<List<Movie>>>()
        movies.value = moviesDummyResource

        assertNotNull(moviesDummyData)
        assertEquals(itemSizeForTesting, moviesDummyData.size)

        `when`(repository.getMovies()).thenReturn(movies)
        val actualMovies = repository.getMovies().value?.data

        verify(repository).getMovies()
        assertNotNull(actualMovies)
        assertEquals(moviesDummyData.size, actualMovies?.size)

        repository.getMovies().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(moviesDummyResource)
    }

    @Test
    fun `fetch tvShow list, they must be not null and has expected sizes`() = runBlocking {
        val tvShowsDummyData = DataGenerator.getAllTVShows()
        val tvShowsDummyResource = Resource.Success(tvShowsDummyData)
        val tvShows = MutableLiveData<Resource<List<TVShow>>>()
        tvShows.value = tvShowsDummyResource

        assertNotNull(tvShowsDummyData)
        assertEquals(itemSizeForTesting, tvShowsDummyData.size)

        `when`(repository.getTVShows()).thenReturn(tvShows)
        val actualTVShows = repository.getTVShows().value?.data

        verify(repository).getTVShows()
        assertNotNull(actualTVShows)
        assertEquals(tvShowsDummyData.size, actualTVShows?.size)

        repository.getTVShows().observeForever(tvShowsObserver)
        verify(tvShowsObserver).onChanged(tvShowsDummyResource)
    }

    @Test
    fun `fetch getDetailMovieOf given movie Id, it must be not null and has expected properties`() =
        runBlocking {
            val movieDummyData = DataGenerator.getDetailMovie(movieIdForTesting)
            val movieDummyResource = Resource.Success(movieDummyData)
            val movie = MutableLiveData<Resource<Movie>>()
            movie.value = movieDummyResource

            assertNotNull(movieDummyData)

            `when`(repository.getDetailMovieOf(movieIdForTesting)).thenReturn(movie)
            val actualMovie = repository.getDetailMovieOf(movieIdForTesting).value?.data

            verify(repository).getDetailMovieOf(movieIdForTesting)
            assertNotNull(actualMovie)
            assertEquals(movieDummyData.title, actualMovie?.title)
            assertEquals(movieDummyData.releasedAt, actualMovie?.releasedAt)
            assertEquals(movieDummyData.status, actualMovie?.status)
            assertEquals(movieDummyData.year, actualMovie?.year)
            assertEquals(movieDummyData.duration, actualMovie?.duration)
            assertEquals(movieDummyData.rate, actualMovie?.rate)
            assertEquals(movieDummyData.rating, actualMovie?.rating)

            repository.getDetailMovieOf(movieIdForTesting).observeForever(detailMovieObserver)
            verify(detailMovieObserver).onChanged(movieDummyResource)
        }

    @Test
    fun `fetch getDetailTVShowOf given tvShow Id, it must be not null and has expected properties`() =
        runBlocking {
            val tvShowDummyData = DataGenerator.getDetailTvShow(tvShowIdForTesting)
            val tvShowDummyResource = Resource.Success(tvShowDummyData)
            val tvShow = MutableLiveData<Resource<TVShow>>()
            tvShow.value = tvShowDummyResource

            assertNotNull(tvShowDummyData)

            `when`(repository.getDetailTVShowOf(tvShowIdForTesting)).thenReturn(tvShow)
            val actualTVShow = repository.getDetailTVShowOf(tvShowIdForTesting).value?.data

            verify(repository).getDetailTVShowOf(tvShowIdForTesting)
            assertNotNull(actualTVShow)
            assertEquals(tvShowDummyData.title, actualTVShow?.title)
            assertEquals(tvShowDummyData.releasedAt, actualTVShow?.releasedAt)
            assertEquals(tvShowDummyData.status, actualTVShow?.status)
            assertEquals(tvShowDummyData.year, actualTVShow?.year)
            assertEquals(tvShowDummyData.duration, actualTVShow?.duration)
            assertEquals(tvShowDummyData.rate, actualTVShow?.rate)
            assertEquals(tvShowDummyData.rating, actualTVShow?.rating)

            repository.getDetailTVShowOf(tvShowIdForTesting).observeForever(detailTVShowObserver)
            verify(detailTVShowObserver).onChanged(tvShowDummyResource)
        }


    @Test
    fun `fetch getSimilarMoviesOf given movie id, they must be not null and has expected sizes`() =
        runBlocking {
            val similarMoviesDummyData = DataGenerator.getSimilarMovies(movieIdForTesting)
            val similarMoviesDummyResource = Resource.Success(similarMoviesDummyData)
            val similarMovies = MutableLiveData<Resource<List<Movie>>>()
            similarMovies.value = similarMoviesDummyResource

            assertNotNull(similarMoviesDummyData)
            assertEquals(itemSizeForTesting, similarMoviesDummyData.size)

            `when`(repository.getSimilarMoviesOf(movieIdForTesting)).thenReturn(similarMovies)
            val actualSimilarMovies = repository.getSimilarMoviesOf(movieIdForTesting).value?.data

            verify(repository).getSimilarMoviesOf(movieIdForTesting)
            assertNotNull(actualSimilarMovies)
            assertEquals(similarMoviesDummyData.size, actualSimilarMovies?.size)

            repository.getSimilarMoviesOf(movieIdForTesting).observeForever(moviesObserver)
            verify(moviesObserver).onChanged(similarMoviesDummyResource)
        }

    @Test
    fun `fetch getSimilarTVShowsOf given tvShow id, they must be not null and has expected sizes`() =
        runBlocking {
            val similarTVShowsDummyData = DataGenerator.getSimilarTVShows(tvShowIdForTesting)
            val similarTVShowsDummyResource = Resource.Success(similarTVShowsDummyData)
            val similarTVShows = MutableLiveData<Resource<List<TVShow>>>()
            similarTVShows.value = similarTVShowsDummyResource

            assertNotNull(similarTVShowsDummyData)
            assertEquals(itemSizeForTesting, similarTVShowsDummyData.size)

            `when`(repository.getSimilarTVShowsOf(tvShowIdForTesting)).thenReturn(similarTVShows)
            val actualSimilarTVShows =
                repository.getSimilarTVShowsOf(tvShowIdForTesting).value?.data

            verify(repository).getSimilarTVShowsOf(tvShowIdForTesting)
            assertNotNull(actualSimilarTVShows)
            assertEquals(similarTVShowsDummyData.size, actualSimilarTVShows?.size)

            repository.getSimilarTVShowsOf(tvShowIdForTesting).observeForever(tvShowsObserver)
            verify(tvShowsObserver).onChanged(similarTVShowsDummyResource)
        }
}
