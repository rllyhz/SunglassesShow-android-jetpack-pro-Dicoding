package id.rllyhz.sunglassesshow.ui.features.movie.content

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.api.ApiEndpoint.Companion.IMAGE_URL
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.databinding.FragmentContentBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.detail.DetailViewModel
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.ViewModelFactory
import id.rllyhz.sunglassesshow.utils.getDateInString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieContentFragment : Fragment(), SimilarContentListAdapter.SimilarContentItemCallback {
    private lateinit var _binding: FragmentContentBinding

    private lateinit var viewModel: DetailViewModel
    private lateinit var similarContentListAdapter: SimilarContentListAdapter
    private lateinit var currentMovie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance(requireContext().applicationContext as Application)
        )[DetailViewModel::class.java]

        currentMovie = arguments?.getParcelable(PARAMS_MOVIE)!!

        similarContentListAdapter = SimilarContentListAdapter()
        similarContentListAdapter.setItemCallback(this)
        _binding.rvSimilarContentDetail.adapter = similarContentListAdapter

        viewModel.detailMovie.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> setupUI(resource.data)
                is Resource.Loading -> showProgressbar(true)
                is Resource.Error -> showProgressbar(false)
                else -> Unit
            }
        }

        viewModel.initDetailMovie(currentMovie)
        viewModel.isMovieFavorited(currentMovie)
    }

    private fun setupUI(movie: Movie?) {
        showProgressbar(false)

        with(_binding) {

            if (movie != null) {
                rbDetail.rating = movie.rating

                tvTitleDetail.text =
                    resources.getString(
                        R.string.title_format,
                        movie.title,
                        movie.year.toString()
                    )

                tvDurationDetail.text = movie.duration
                tvGenresDetail.text = movie.genres
                tvSimilarContentLabelDetail.text =
                    resources.getString(R.string.detail_similar_movies_label)
                tvRateDetail.text =
                    resources.getString(R.string.rate_format, movie.rate.toString())
                tvStatusDetail.text =
                    resources.getString(R.string.status_format, movie.status ?: "")
                tvReleasedAtDetail.text =
                    resources.getString(R.string.released_at_format, movie.getDateInString())
                tvSynopsisDetail.text = movie.synopsis

                Glide.with(root)
                    .load(IMAGE_URL + movie.backdropPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.bg_poster_error)
                    .placeholder(R.drawable.bg_poster_placeholder)
                    .into(ivPosterBgDetail)

                Glide.with(root)
                    .load(IMAGE_URL + movie.posterPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.bg_poster_error)
                    .placeholder(R.drawable.bg_poster_placeholder)
                    .into(ivPosterDetail)

                ivPosterBgDetail.contentDescription = movie.title
                ivPosterDetail.contentDescription = movie.title
            }

            viewModel.isMovieFavorited.observe(viewLifecycleOwner) { isFavorited ->
                toggleBtnFav.isChecked = isFavorited
            }

            setupSimilarContentUI()

            ivViewTrailerDetail.setOnClickListener { }
            btnWatchDetail.setOnClickListener { }

            toggleBtnFav.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    movie?.apply {
                        when (toggleBtnFav.isChecked) {
                            false -> {
                                viewModel.deleteFavMovie(this)
                                showToast(requireContext().getString(R.string.favorites_deleted_movie_message))
                            }
                            true -> {
                                viewModel.addFavMovie(this)
                                showToast(requireContext().getString(R.string.favorites_added_movie_message))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupSimilarContentUI() {
        with(_binding) {
            with(rvSimilarContentDetail) {
                layoutManager =
                    LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = similarContentListAdapter
            }
        }

        viewModel.similarMovies.observe(viewLifecycleOwner) { resource ->
            with(_binding) {

                when (resource) {
                    is Resource.Success -> {
                        if (resource.data?.isEmpty() == true) {
                            tvNoSimilarText.visibility = View.VISIBLE
                            rvSimilarContentDetail.visibility = View.GONE
                        } else {
                            tvNoSimilarText.visibility = View.GONE
                            rvSimilarContentDetail.visibility = View.VISIBLE
                            similarContentListAdapter.submitList(resource.data)
                        }

                        showProgressbarSimilarContents(false)
                    }
                    is Resource.Error -> {
                        tvNoSimilarText.visibility = View.VISIBLE
                        showProgressbarSimilarContents(false)
                    }
                    is Resource.Loading -> showProgressbarSimilarContents(true)
                    else -> Unit
                }
            }
        }

        viewModel.initSimilarMovies(currentMovie)
    }

    private fun showProgressbar(state: Boolean) {
        with(_binding) {
            if (state)
                progressbar.visibility = View.VISIBLE
            else
                progressbar.visibility = View.GONE
        }
    }

    private fun showProgressbarSimilarContents(state: Boolean) {
        with(_binding) {
            if (state)
                progressbarSimilarContents.visibility = View.VISIBLE
            else
                progressbarSimilarContents.visibility = View.GONE
        }
    }

    private suspend fun showToast(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(movie: Movie) {
        with(Intent(requireActivity(), DetailActivity::class.java)) {
            putExtra(DetailActivity.EXTRA_CONTENT_MOVIE, movie)
            putExtra(DetailActivity.GOTO_MOVIE_DETAIL, true)

            requireActivity().startActivity(this)
        }
    }

    companion object {
        val TAG: String = MovieContentFragment::class.java.simpleName
        private const val PARAMS_MOVIE = "PARAMS_MOVIE"

        fun newInstance(movie: Movie): MovieContentFragment =
            MovieContentFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAMS_MOVIE, movie)
                }
            }
    }
}