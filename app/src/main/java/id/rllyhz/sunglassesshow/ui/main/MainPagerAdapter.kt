package id.rllyhz.sunglassesshow.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.ui.features.movie.list.MovieListFragment
import id.rllyhz.sunglassesshow.ui.features.tvshow.list.TVShowListFragment

class MainPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieListFragment.newInstance()
            1 -> TVShowListFragment.newInstance()
            else -> Fragment()
        }

    override fun getCount(): Int = MAIN_TAB_TITLES.size

    override fun getPageTitle(position: Int): CharSequence =
        context.resources.getString(MAIN_TAB_TITLES[position])

    companion object {
        @StringRes
        private val MAIN_TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
    }
}