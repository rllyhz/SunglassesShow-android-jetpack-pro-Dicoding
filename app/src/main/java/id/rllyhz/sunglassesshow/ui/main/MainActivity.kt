package id.rllyhz.sunglassesshow.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.databinding.ActivityMainBinding
import id.rllyhz.sunglassesshow.ui.favorites.FavoritesActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        // this approach explained in the official documentation
        // when there is no task when launching app
        setTheme(R.style.Theme_SunGlassesShow)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        supportActionBar?.elevation = 0f

        val mainPagerAdapter = MainPagerAdapter(this, supportFragmentManager)

        binding.apply {
            with(viewPagerMain) {
                adapter = mainPagerAdapter
            }

            tabLayoutMain.setupWithViewPager(viewPagerMain)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_item_favorites -> {
            startActivity(
                Intent(this@MainActivity, FavoritesActivity::class.java)
            )
            true
        }

        else -> super.onOptionsItemSelected(item)
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
}