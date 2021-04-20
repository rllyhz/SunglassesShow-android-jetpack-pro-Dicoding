package id.rllyhz.sunglassesshow.ui.detail

import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.ui.main.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var currentMovie: Movie
    private lateinit var currentTVShow: TVShow

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
        viewModel = DetailViewModel()
        currentMovie = mainViewModel.getMovies()[0]
        currentTVShow = mainViewModel.getTVShows()[0]
    }

    @Test
    fun `currentMovie must not be null`() {
        assertNotNull(currentMovie)
    }

    @Test
    fun `currentTVShow must not be null`() {
        assertNotNull(currentTVShow)
    }

    @Test
    fun `get all similar movie data, they can not be null and has the correct size`() {
        val similarMovies = viewModel.getSimilarMovieOf(currentMovie)
        val expectedSize = 6

        assertNotNull(similarMovies)
        assertEquals(expectedSize, similarMovies.size)
    }

    @Test
    fun `get all similar tv show data, they can not be null and has the correct size`() {
        val similarTVShows = viewModel.getSimilarTVShowOf(currentTVShow)
        val expectedSize = 6

        assertNotNull(similarTVShows)
        assertEquals(expectedSize, similarTVShows.size)
    }
}