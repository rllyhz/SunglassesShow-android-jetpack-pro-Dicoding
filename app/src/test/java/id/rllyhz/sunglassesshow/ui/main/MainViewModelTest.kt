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
    fun `get all movies data, they can not be null and has the correct size`() {
        val movies = viewModel.getMovies()
        val expectedSize = 17

        assertNotNull(movies)
        assertEquals(expectedSize, movies.size)
    }

    @Test
    fun `get all tv shows data, they can not be null and has the correct size`() {
        val tvShows = viewModel.getTVShows()
        val expectedSize = 19

        assertNotNull(tvShows)
        assertEquals(expectedSize, tvShows.size)
    }
}