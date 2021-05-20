package id.rllyhz.sunglassesshow.ui.favorites

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesActivityTest {
    private val positionItemForTesting = 0

    @get:Rule
    var activityRule = ActivityScenarioRule(FavoritesActivity::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
        addItemsToFav()
    }

    private fun addItemsToFav() {
        //
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
        removeItemsFromFav()
    }

    private fun removeItemsFromFav() {
        //
    }

    @Test
    fun loadFavMoviesIfExists_makeSureRecyclerViewShownAndScrollableAndClickable() {
        onView(withId(R.id.rv_fav_movie_list)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.rv_fav_movie_list)).perform(
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
    }

    @Test
    fun loadFavTVSHowsIfExists_makeSureRecyclerViewShownAndScrollableAndClickable() {
        onView(withId(R.id.view_pager_favorites)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_favorites)).perform(swipeLeft())
        onView(withId(R.id.rv_fav_tvshow_list)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_fav_tvshow_list)).check(matches(isDisplayed())).perform(swipeUp())
        onView(withId(R.id.rv_fav_tvshow_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                click()
            )
        )

        onView(withId(R.id.iv_view_trailer_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())
        onView(withId(R.id.tv_synopsis_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())
        onView(withId(R.id.btn_watch_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())
        onView(withId(R.id.tv_similar_content_label_detail)).check(matches(isDisplayed()))
            .perform(swipeUp())
    }

    @Test
    fun swipeLeftFavMoviesItemForDeleting() {
        onView(withId(R.id.rv_fav_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                swipeLeft()
            )
        )
    }

    @Test
    fun swipeLeftFavTVShowsItemForDeleting() {
        onView(withId(R.id.view_pager_favorites)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager_favorites)).perform(swipeLeft())

        onView(withId(R.id.rv_fav_tvshow_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tvshow_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                positionItemForTesting,
                swipeLeft()
            )
        )
    }
}