package id.rllyhz.sunglassesshow.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.utils.CoroutineTestRule
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.asFavModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SunGlassesShowRepositoryTest {
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

    @Mock
    private lateinit var favMoviesObserver: Observer<PagedList<FavMovie>>

    @Mock
    private lateinit var favTVShowsObserver: Observer<PagedList<FavTVShow>>

    @Mock
    private lateinit var favMoviesPagedList: PagedList<FavMovie>

    @Mock
    private lateinit var favTVShowsPagedList: PagedList<FavTVShow>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val itemSizeForTesting = 20
    private val movieIdForTesting = 460465
    private val tvShowIdForTesting = 1416

    @Test
    fun `fetch movies list, they must be not null and has expected sizes`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
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
    fun `fetch tvShow list, they must be not null and has expected sizes`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
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
        coroutineTestRule.testDispatcher.runBlockingTest {
            val movieDummyData = DataGenerator.getDetailMovie()
            val movieDummyResource = Resource.Success(movieDummyData)
            val movie = MutableLiveData<Resource<Movie>>()
            movie.value = movieDummyResource

            assertNotNull(movieDummyData)

            `when`(repository.getDetailMovieOf(movieIdForTesting)).thenReturn(movie)
            val actualMovie = repository.getDetailMovieOf(movieIdForTesting).value?.data

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

            repository.getDetailMovieOf(movieIdForTesting).observeForever(detailMovieObserver)
            verify(detailMovieObserver).onChanged(movieDummyResource)
        }

    @Test
    fun `fetch getDetailTVShowOf given tvShow Id, it must be not null and has expected properties`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            val tvShowDummyData = DataGenerator.getDetailTvShow()
            val tvShowDummyResource = Resource.Success(tvShowDummyData)
            val tvShow = MutableLiveData<Resource<TVShow>>()
            tvShow.value = tvShowDummyResource

            assertNotNull(tvShowDummyData)

            `when`(repository.getDetailTVShowOf(tvShowIdForTesting)).thenReturn(tvShow)
            val actualTVShow = repository.getDetailTVShowOf(tvShowIdForTesting).value?.data

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

            repository.getDetailTVShowOf(tvShowIdForTesting).observeForever(detailTVShowObserver)
            verify(detailTVShowObserver).onChanged(tvShowDummyResource)
        }


    @Test
    fun `fetch getSimilarMoviesOf given movie id, they must be not null and has expected sizes`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            val similarMoviesDummyData = DataGenerator.getSimilarMovies()
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
        coroutineTestRule.testDispatcher.runBlockingTest {
            val similarTVShowsDummyData = DataGenerator.getSimilarTVShows()
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

    @Test
    fun `get all favorite movies and they must not be null`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            `when`(favMoviesPagedList.size).thenReturn(6)
            val favMoviesDummyData = MutableLiveData(favMoviesPagedList)

            `when`(repository.getFavMovies()).thenReturn(favMoviesDummyData)
            val actualData = repository.getFavMovies().value
            verify(repository).getFavMovies()
            assertNotNull(actualData)
            assertEquals(6, actualData?.size)

            repository.getFavMovies().observeForever(favMoviesObserver)
            favMoviesObserver.onChanged(favMoviesPagedList)
        }

    @Test
    fun `get all favorite tv shows and they must not be null`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            `when`(favTVShowsPagedList.size).thenReturn(6)
            val favTVShowsDummyData = MutableLiveData(favTVShowsPagedList)

            `when`(repository.getFavTVShows()).thenReturn(favTVShowsDummyData)
            val actualData = repository.getFavTVShows().value
            verify(repository).getFavTVShows()
            assertNotNull(actualData)
            assertEquals(6, actualData?.size)

            repository.getFavTVShows().observeForever(favTVShowsObserver)
            favTVShowsObserver.onChanged(favTVShowsPagedList)
        }

    @Test
    fun `add and then remove favorite movie`() = coroutineTestRule.testDispatcher.runBlockingTest {
        val favMovieDataDummy = DataGenerator.getDetailMovie().asFavModel()

        // adding scenario
        `when`(repository.addFavMovie(favMovieDataDummy)).thenReturn(favMovieDataDummy.id.toLong())
        val addedFavMovie = repository.addFavMovie(favMovieDataDummy)
        assertNotNull(addedFavMovie)

        // removing scenario
        `when`(repository.deleteFavMovie(favMovieDataDummy)).thenReturn(favMovieDataDummy.id)
        val removedFavMovie = repository.deleteFavMovie(favMovieDataDummy)
        assertNotNull(removedFavMovie)
    }

    @Test
    fun `add and then remove favorite tv show`() = coroutineTestRule.testDispatcher.runBlockingTest  {
        val favTVShowsDataDummy = DataGenerator.getDetailTvShow().asFavModel()

        // adding scenario
        `when`(repository.addFavTVShow(favTVShowsDataDummy)).thenReturn(favTVShowsDataDummy.id.toLong())
        val addedFavMovie = repository.addFavTVShow(favTVShowsDataDummy)
        assertNotNull(addedFavMovie)

        // removing scenario
        `when`(repository.deleteFavTVShow(favTVShowsDataDummy)).thenReturn(favTVShowsDataDummy.id)
        val removedFavMovie = repository.deleteFavTVShow(favTVShowsDataDummy)
        assertNotNull(removedFavMovie)
    }

    @Test
    fun `get favorite movie by given id`() = coroutineTestRule.testDispatcher.runBlockingTest  {
        val favMovieDataDummy = DataGenerator.getDetailMovie().asFavModel()
        `when`(repository.getFavMovieById(favMovieDataDummy.id)).thenReturn(favMovieDataDummy)

        val actualData = repository.getFavMovieById(favMovieDataDummy.id)
        assertNotNull(actualData)
        assertEquals(
            repository.getFavMovieById(favMovieDataDummy.id)?.title,
            actualData?.title
        )
    }

    @Test
    fun `get favorite tv show by given id`() = coroutineTestRule.testDispatcher.runBlockingTest  {
        val favTVShowDataDummy = DataGenerator.getDetailTvShow().asFavModel()
        `when`(repository.getFavTVShowById(favTVShowDataDummy.id)).thenReturn(favTVShowDataDummy)

        val actualData = repository.getFavTVShowById(favTVShowDataDummy.id)
        assertNotNull(actualData)
        assertEquals(
            repository.getFavTVShowById(favTVShowDataDummy.id)?.title,
            actualData?.title
        )
    }
}
