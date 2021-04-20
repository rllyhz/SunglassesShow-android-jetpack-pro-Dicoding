package id.rllyhz.sunglassesshow.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.databinding.ActivityMainBinding

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
}