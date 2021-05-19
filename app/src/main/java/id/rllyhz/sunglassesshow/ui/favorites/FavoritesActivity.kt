package id.rllyhz.sunglassesshow.ui.favorites

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupUI()
    }

    private fun setupUI() {
        supportActionBar?.elevation = 0f

        val favoritesPagerAdapter = FavoritesPagerAdapter(this, supportFragmentManager)

        binding.apply {
            with(viewPagerFavorites) {
                adapter = favoritesPagerAdapter
            }

            tabLayoutFavorites.setupWithViewPager(viewPagerFavorites)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.title = getString(R.string.favorites_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onLoading(state: Boolean) {
        showProgressbar(state)
    }

    private fun showProgressbar(state: Boolean) {
        with(binding) {
            if (state)
                progressbar.visibility = View.VISIBLE
            else
                progressbar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}