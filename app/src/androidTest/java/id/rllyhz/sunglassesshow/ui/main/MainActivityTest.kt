package id.rllyhz.sunglassesshow.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.utils.DataGenerator
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesDummyData = DataGenerator.getAllMovies()
    private val tvShowsDummyData = DataGenerator.getAllTVShows()
    private val positionItemForTesting = 1

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadAndShowDataMoviesAndMakeSureTheyAreClickable() {

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
    fun swipeToTvShowsTabPreview() {
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

    @Test
    fun detailActivitySimulationAndMakeSureEverythingOnItDisplayedAndClickable() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster_bg_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genres_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_director_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_view_trailer_detail)).perform(click())

        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_watch_detail)).perform(click())
    }
}