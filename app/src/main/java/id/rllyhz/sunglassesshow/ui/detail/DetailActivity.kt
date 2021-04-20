package id.rllyhz.sunglassesshow.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.databinding.ActivityDetailBinding
import id.rllyhz.sunglassesshow.ui.features.movie.content.MovieContentFragment

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var currentMovie: Movie? = null
    private var currentTvShow: TVShow? = null
    private var isMovieContentState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(intent) {
            when {
                getBooleanExtra(GOTO_MOVIE_DETAIL, false) -> {
                    currentMovie = getParcelableExtra(EXTRA_CONTENT_MOVIE)
                    isMovieContentState = true
                }
                getBooleanExtra(GOTO_TV_SHOW_DETAIL, false) -> {
                    currentTvShow = getParcelableExtra(EXTRA_CONTENT_TV_SHOW)
                    isMovieContentState = false
                }
                else -> {
                    finish()
                    return
                }
            }

            setupUI()
        }
    }

    private fun setupUI() {
        when (isMovieContentState) {
            true -> {
                currentMovie?.let { movie ->
                    var movieFragment =
                        supportFragmentManager.findFragmentByTag(MovieContentFragment.TAG)

                    if (movieFragment == null) {
                        movieFragment = MovieContentFragment.newInstance(movie)
                    }

                    setupFragment(movieFragment, MovieContentFragment.TAG)
                }
            }
            else -> {
                currentTvShow?.let {
                    val tvShowFragment = Fragment()
                    setupFragment(tvShowFragment, "")
                }
            }
        }
    }

    private fun setupFragment(activeFragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment_container_detail, activeFragment, tag)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val EXTRA_CONTENT_MOVIE = "EXTRA_CONTENT_MOVIE"
        const val EXTRA_CONTENT_TV_SHOW = "EXTRA_CONTENT_TV_SHOW"
        const val GOTO_MOVIE_DETAIL = "GOTO_MOVIE_DETAIL"
        const val GOTO_TV_SHOW_DETAIL = "GOTO_TV_SHOW_DETAIL"
    }
}