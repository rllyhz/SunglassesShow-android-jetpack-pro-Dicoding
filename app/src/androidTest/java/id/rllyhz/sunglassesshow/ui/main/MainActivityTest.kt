package id.rllyhz.sunglassesshow.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesDummyData = DataGenerator.getAllMovies()
    private val tvShowsDummyData = DataGenerator.getAllTVShows()
    private val positionItemForTesting = 3

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadAndShowDataMovies_makeSureTheyAreClickable() {

        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                moviesDummyData.size
            )
        )

        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )
    }

    @Test
    fun swipeToTheLeftTvShowsTabPreview() {
        onView(withId(R.id.view_pager_main)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_main)).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow_list)).check(matches(isDisplayed()))
    }

    @Test
    fun loadAndShowDataTVShowsAndMakeSureTheyAreClickable() {
        onView(withId(R.id.view_pager_main)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_main)).perform(swipeLeft())
        onView(withId(R.id.rv_tvshow_list)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tvshow_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShowsDummyData.size
            )
        )

        onView(withId(R.id.rv_tvshow_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )
    }
}