package id.rllyhz.sunglassesshow.ui.main

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.utils.DataGenerator
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesDummyData = DataGenerator.getAllMovies()
    private val tvShowsDummyData = DataGenerator.getAllTVShows()
    private val positionItemForTesting = 1
    private val itemTesting = moviesDummyData[positionItemForTesting]
    private val similarContents = DataGenerator.getSimilarMoviesDummyData(itemTesting)

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
        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed())).perform(swipeUp())

        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_watch_detail)).perform(click())
        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed())).perform(swipeUp())

        onView(withId(R.id.rv_similar_content_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_similar_content_detail)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                similarContents.size
            )
        )

        onView(withId(R.id.rv_similar_content_detail)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )
    }

    private fun getStringResources(
        ctx: Context,
        resId: Int,
        string1: String? = null,
        string2: String? = null
    ): String =
        when {
            string1 == null -> ""
            string2 == null -> ctx.getString(resId, string1)
            else -> ctx.getString(resId, string1, string2)
        }

    @Test
    fun detailActivitySimulationAndMakeSureEveryTextViewComponentsShowTheCorrectText() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val expectedTitle = getStringResources(
            context,
            R.string.title_format,
            itemTesting.title,
            itemTesting.year.toString()
        )

        val expectedRate = getStringResources(
            context,
            R.string.rate_format,
            itemTesting.rate
        )

        val expectedDirector = getStringResources(
            context,
            R.string.director_format,
            itemTesting.director
        )

        val expectedGenres = itemTesting.genres
        val expectedSynopsis = itemTesting.synopsis
        val expectedDuration = itemTesting.duration

        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )

        onView(withId(R.id.tv_title_detail)).check(matches(withText(expectedTitle)))
        onView(withId(R.id.tv_genres_detail)).check(matches(withText(expectedGenres)))
        onView(withId(R.id.tv_rate_detail)).check(matches(withText(expectedRate)))
        onView(withId(R.id.tv_director_detail)).check(matches(withText(expectedDirector)))
        onView(withId(R.id.tv_synopsis_detail)).check(matches(withText(expectedSynopsis)))
        onView(withId(R.id.tv_duration_detail)).check(matches(withText(expectedDuration)))
    }
}