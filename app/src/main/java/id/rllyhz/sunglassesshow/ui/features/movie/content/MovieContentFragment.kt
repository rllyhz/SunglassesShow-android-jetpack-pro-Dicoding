package id.rllyhz.sunglassesshow.ui.features.movie.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class MovieContentFragment : Fragment(), SimilarContentListAdapter.SimilarContentItemCallback {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel
    private lateinit var similarContentListAdapter: SimilarContentListAdapter
    private lateinit var currentMovie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance()
        )[DetailViewModel::class.java]

        currentMovie = arguments?.getParcelable(PARAMS_MOVIE)!!

        similarContentListAdapter = SimilarContentListAdapter()
        similarContentListAdapter.setItemCallback(this)

        viewModel.detailMovie.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> setupUI(resource.data)
                is Resource.Loading -> showProgressbar(true)
                is Resource.Error -> showProgressbar(false)
                else -> Unit
            }
        }

        viewModel.initDetailMovie(currentMovie)
    }

    private fun setupUI(movie: Movie?) {
        showProgressbar(false)

        with(binding) {

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
            }

            setupSimilarContentUI()

            ivViewTrailerDetail.setOnClickListener { }
            btnWatchDetail.setOnClickListener { }
        }
    }

    private fun setupSimilarContentUI() {
        with(binding) {
            with(rvSimilarContentDetail) {
                layoutManager =
                    LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = similarContentListAdapter
            }
        }

        viewModel.similarMovies.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    if (resource.data?.isEmpty() == true) {
                        binding.tvNoSimilarText.visibility = View.VISIBLE
                        binding.rvSimilarContentDetail.visibility = View.GONE
                    } else {
                        binding.tvNoSimilarText.visibility = View.GONE
                        binding.rvSimilarContentDetail.visibility = View.VISIBLE
                        similarContentListAdapter.submitList(resource.data)
                    }
                }
                is Resource.Error -> binding.tvNoSimilarText.visibility = View.VISIBLE
                else -> Unit
            }
        }

        viewModel.initSimilarMovies(currentMovie)
    }

    private fun showProgressbar(state: Boolean) {
        with(binding) {
            if (state)
                progressbar.visibility = View.VISIBLE
            else
                progressbar.visibility = View.GONE
        }
    }

    override fun onClick(movie: Movie) {
        with(Intent(requireActivity(), DetailActivity::class.java)) {
            putExtra(DetailActivity.EXTRA_CONTENT_MOVIE, movie)
            putExtra(DetailActivity.GOTO_MOVIE_DETAIL, true)

            requireActivity().startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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