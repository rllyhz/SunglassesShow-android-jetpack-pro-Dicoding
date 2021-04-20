package id.rllyhz.sunglassesshow.ui.features.movie.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.databinding.FragmentMovieContentBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.detail.DetailViewModel

class MovieContentFragment : Fragment(), SimilarContentListAdapter.SimilarContentItemCallback {
    private var _binding: FragmentMovieContentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var similarContentListAdapter: SimilarContentListAdapter
    private lateinit var currentMovie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentMovie = arguments?.getParcelable(PARAMS_MOVIE)!!

        similarContentListAdapter = SimilarContentListAdapter()
        similarContentListAdapter.setItemCallback(this)
        setupUI()
    }

    private fun setupUI() {
        similarContentListAdapter.submitList(
            viewModel.getSimilarMovieOf(currentMovie).toMutableList()
        )

        with(binding) {
            progressbar.visibility = View.GONE

            rbDetail.rating = currentMovie.rating
            tvTitleDetail.text =
                resources.getString(
                    R.string.title_format,
                    currentMovie.title,
                    currentMovie.year.toString()
                )

            tvDurationDetail.text = currentMovie.duration
            tvGenresDetail.text = currentMovie.genres

            tvRateDetail.text = resources.getString(R.string.rate_format, currentMovie.rate)

            tvDirectorDetail.text =
                resources.getString(R.string.director_format, currentMovie.director)

            tvSynopsisDetail.text = currentMovie.synopsis

            Glide.with(root)
                .load(
                    root.context.resources.getIdentifier(
                        currentMovie.posterName, "drawable",
                        root.context.packageName
                    )
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPosterBgDetail)

            Glide.with(root)
                .load(
                    root.context.resources.getIdentifier(
                        currentMovie.posterName, "drawable",
                        root.context.packageName
                    )
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPosterDetail)

            with(rvSimilarContentDetail) {
                layoutManager =
                    LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = similarContentListAdapter
            }

            ivViewTrailerDetail.setOnClickListener { }
            btnWatchDetail.setOnClickListener { }
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