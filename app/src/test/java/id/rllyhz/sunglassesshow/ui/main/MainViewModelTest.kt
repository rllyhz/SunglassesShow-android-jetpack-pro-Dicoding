package id.rllyhz.sunglassesshow.ui.main

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(17, movies.size)
    }

    @Test
    fun getTVShows() {
        val tvShows = viewModel.getTVShows()
        assertNotNull(tvShows)
        assertEquals(19, tvShows.size)
    }
}