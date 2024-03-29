package id.rllyhz.sunglassesshow.ui.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.ui.main.MainActivity
import id.rllyhz.sunglassesshow.utils.DataGenerator
import id.rllyhz.sunglassesshow.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {
    private val positionItemForTesting = 2
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
    fun detailActivitySimulation_addAndRemoveMovieFromFav() {
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

        // click toggle fav btn at the first time
        onView(withId(R.id.toggle_btn_fav)).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.menu_item_favorites)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.view_pager_favorites)).check(matches(isDisplayed()))

        onView(isRoot()).perform(ViewActions.pressBack())

        // re-click toggle fav btn
        onView(withId(R.id.toggle_btn_fav)).check(matches(isDisplayed())).perform(click())

        // re-check fav activity
        onView(withId(R.id.menu_item_favorites)).check(matches(isDisplayed())).perform(click())
    }
}