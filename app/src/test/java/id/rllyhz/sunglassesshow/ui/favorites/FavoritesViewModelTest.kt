package id.rllyhz.sunglassesshow.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import id.rllyhz.sunglassesshow.data.source.SunGlassesShowRepository
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {
    private lateinit var viewModel: FavoritesViewModel

    @Mock
    private lateinit var repository: SunGlassesShowRepository

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

    @Before
    fun setUp() {
        viewModel = FavoritesViewModel(repository)
    }

    @Test
    fun `get all favorite movies and they must not be null`() {
        `when`(favMoviesPagedList.size).thenReturn(6)
        val favMoviesDummyData = MutableLiveData(favMoviesPagedList)

        `when`(repository.getFavMovies()).thenReturn(favMoviesDummyData)
        verify(repository).getFavMovies()

        assertNotNull(viewModel)

        viewModel.initAllFavMovies()
        Thread.sleep(2000)
        val actualFavMovie = viewModel.favMovies().value
        assertNotNull(actualFavMovie)
        assertEquals(6, actualFavMovie?.size)

        viewModel.favMovies().observeForever(favMoviesObserver)
        verify(favMoviesObserver).onChanged(favMoviesPagedList)
    }

    @Test
    fun `get all favorite tv shows and they must not be null`() {
        `when`(favTVShowsPagedList.size).thenReturn(6)
        val favTVShowsDummyData = MutableLiveData(favTVShowsPagedList)

        `when`(repository.getFavTVShows()).thenReturn(favTVShowsDummyData)
        verify(repository).getFavTVShows()

        assertNotNull(viewModel)

        viewModel.initAllFavTVShows()
        Thread.sleep(2000)
        val actualFavTVShow = viewModel.favTVShows().value
        assertNotNull(actualFavTVShow)
        assertEquals(6, actualFavTVShow?.size)

        viewModel.favTVShows().observeForever(favTVShowsObserver)
        verify(favTVShowsObserver).onChanged(favTVShowsPagedList)
    }
}