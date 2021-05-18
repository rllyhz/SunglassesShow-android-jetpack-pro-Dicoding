package id.rllyhz.sunglassesshow.ui.favorites

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.rllyhz.sunglassesshow.R

class FavoritesPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> Fragment()
            1 -> Fragment()
            else -> Fragment()
        }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence =
        context.resources.getString(FAV_TAB_TITLES[position])

    companion object {
        @StringRes
        private val FAV_TAB_TITLES =
            intArrayOf(R.string.favorites_movie, R.string.favorites_tv_show)
    }
}