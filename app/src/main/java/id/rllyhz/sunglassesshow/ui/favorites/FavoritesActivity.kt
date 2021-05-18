package id.rllyhz.sunglassesshow.ui.favorites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.favorites_title)
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
}