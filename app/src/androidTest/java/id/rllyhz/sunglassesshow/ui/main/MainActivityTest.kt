package id.rllyhz.sunglassesshow.ui.main

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.EspressoIdlingResource
import id.rllyhz.sunglassesshow.utils.getDateInString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesDummyData = DataGenerator.getAllMovies()
    private val tvShowsDummyData = DataGenerator.getAllTVShows()
    private val positionItemForTesting = 2
    private val movieItemTesting = DataGenerator.getDetailMovie()
    private val similarContents = DataGenerator.getSimilarMovies()

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

    @Test
    fun detailActivitySimulation_makeSureEverythingOnItDisplayedAndClickable() {
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
        onView(withId(R.id.tv_status_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_view_trailer_detail)).perform(click())

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed())).perform(swipeUp())

        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_watch_detail)).perform(click())

        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_similar_content_label_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())

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
    fun detailActivitySimulation_makeSureEveryTextViewComponentsShowTheCorrectText() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val expectedTitle = getStringResources(
            context,
            R.string.title_format,
            movieItemTesting.title,
            movieItemTesting.year.toString()
        )

        val expectedRate = getStringResources(
            context,
            R.string.rate_format,
            movieItemTesting.rate.toString()
        )

        val expectedStatus = getStringResources(
            context,
            R.string.status_format,
            movieItemTesting.status ?: ""
        )

        val expectedReleasedAt = getStringResources(
            context,
            R.string.released_at_format,
            movieItemTesting.getDateInString()
        )

        val expectedGenres = movieItemTesting.genres
        val expectedSynopsis = movieItemTesting.synopsis
        val expectedDuration = movieItemTesting.duration

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
        onView(withId(R.id.tv_released_at_detail)).check(matches(withText(expectedReleasedAt)))
        onView(withId(R.id.tv_status_detail)).check(matches(withText(expectedStatus)))
        onView(withId(R.id.tv_synopsis_detail)).check(matches(withText(expectedSynopsis)))
        onView(withId(R.id.tv_duration_detail)).check(matches(withText(expectedDuration)))
    }

    @Test
    fun detailActivitySimulationAndThenLoadAndShowSimilarMovies_makeSureTheyAreClickable() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_similar_content_label_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())

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

    @Test
    fun detailActivitySimulation_addMovieToFav() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_similar_content_label_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())

        onView(withId(R.id.toggle_btn_fav)).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.menu_item_favorites)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.view_pager_favorites)).check(matches(isDisplayed()))
    }

    @Test
    fun detailActivitySimulation_addTVShowToFav() {
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

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.tv_similar_content_label_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())

        onView(withId(R.id.toggle_btn_fav)).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.menu_item_favorites)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.view_pager_favorites)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_favorites)).perform(swipeLeft())
        onView(withId(R.id.rv_fav_tvshow_list)).check(matches(isDisplayed()))
    }
}