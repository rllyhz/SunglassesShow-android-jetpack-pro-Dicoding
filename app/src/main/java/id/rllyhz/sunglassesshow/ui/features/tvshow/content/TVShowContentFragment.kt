package id.rllyhz.sunglassesshow.ui.features.tvshow.content

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
import id.rllyhz.sunglassesshow.api.ApiEndpoint
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.databinding.FragmentContentBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.detail.DetailViewModel
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.ViewModelFactory
import id.rllyhz.sunglassesshow.utils.getDateInString

class TVShowContentFragment : Fragment(), SimilarContentListAdapter.SimilarContentItemCallback {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel
    private lateinit var similarContentListAdapter: SimilarContentListAdapter
    private lateinit var currentTvShow: TVShow

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

        currentTvShow = arguments?.getParcelable(PARAMS_MOVIE)!!

        similarContentListAdapter = SimilarContentListAdapter()
        similarContentListAdapter.setItemCallback(this)

        viewModel.detailTVShow.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> setupUI(resource.data)
                is Resource.Loading -> showProgressbar(true)
                is Resource.Error -> showProgressbar(false)
                else -> Unit
            }
        }

        viewModel.initDetailTVShow(currentTvShow)
    }

    private fun setupUI(tvShow: TVShow?) {
        showProgressbar(false)

        with(binding) {

            if (tvShow != null) {
                rbDetail.rating = tvShow.rating

                tvTitleDetail.text =
                    resources.getString(
                        R.string.title_format,
                        tvShow.title,
                        tvShow.year.toString()
                    )

                tvDurationDetail.text = tvShow.duration
                tvGenresDetail.text = tvShow.genres
                tvSimilarContentLabelDetail.text =
                    resources.getString(R.string.detail_similar_movies_label)
                tvRateDetail.text =
                    resources.getString(R.string.rate_format, tvShow.rate.toString())
                tvStatusDetail.text =
                    resources.getString(R.string.status_format, tvShow.status ?: "")
                tvReleasedAtDetail.text =
                    resources.getString(R.string.released_at_format, tvShow.getDateInString())
                tvSynopsisDetail.text = tvShow.synopsis

                Glide.with(root)
                    .load(ApiEndpoint.IMAGE_URL + tvShow.backdropPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.bg_poster_placeholder)
                    .into(ivPosterBgDetail)

                Glide.with(root)
                    .load(ApiEndpoint.IMAGE_URL + tvShow.posterPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.bg_poster_placeholder)
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

        viewModel.similarTVShows.observe(viewLifecycleOwner) { resource ->
            with(binding) {

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

        viewModel.initSimilarTVShows(currentTvShow)
    }

    override fun onClick(tvShow: TVShow) {
        with(Intent(requireActivity(), DetailActivity::class.java)) {
            putExtra(DetailActivity.EXTRA_CONTENT_TV_SHOW, tvShow)
            putExtra(DetailActivity.GOTO_TV_SHOW_DETAIL, true)

            requireActivity().startActivity(this)
        }
    }

    private fun showProgressbar(state: Boolean) {
        with(binding) {
            if (state)
                progressbar.visibility = View.VISIBLE
            else
                progressbar.visibility = View.GONE
        }
    }

    private fun showProgressbarSimilarContents(state: Boolean) {
        with(binding) {
            if (state)
                progressbarSimilarContents.visibility = View.VISIBLE
            else
                progressbarSimilarContents.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG: String = TVShowContentFragment::class.java.simpleName
        private const val PARAMS_MOVIE = "PARAMS_MOVIE"

        fun newInstance(tvShow: TVShow): TVShowContentFragment =
            TVShowContentFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAMS_MOVIE, tvShow)
                }
            }
    }
}