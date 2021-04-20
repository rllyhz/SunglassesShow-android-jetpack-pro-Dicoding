package id.rllyhz.sunglassesshow.ui.features.tvshow.content

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
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.databinding.FragmentContentBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.detail.DetailViewModel

class TVShowContentFragment : Fragment(), SimilarContentListAdapter.SimilarContentItemCallback {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
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

        currentTvShow = arguments?.getParcelable(PARAMS_MOVIE)!!

        similarContentListAdapter = SimilarContentListAdapter()
        similarContentListAdapter.setItemCallback(this)
        setupUI()
    }

    private fun setupUI() {
        similarContentListAdapter.submitList(
            viewModel.getSimilarTVShowOf(currentTvShow).toMutableList()
        )

        with(binding) {
            progressbar.visibility = View.GONE

            rbDetail.rating = currentTvShow.rating
            tvTitleDetail.text =
                resources.getString(
                    R.string.title_format,
                    currentTvShow.title,
                    currentTvShow.year.toString()
                )

            tvDurationDetail.text = currentTvShow.duration
            tvGenresDetail.text = currentTvShow.genres
            tvSimilarContentLabelDetail.text =
                resources.getString(R.string.detail_similar_tv_show_label)

            tvRateDetail.text = resources.getString(R.string.rate_format, currentTvShow.rate)

            tvDirectorDetail.text =
                resources.getString(R.string.creator_format, currentTvShow.creator)

            tvSynopsisDetail.text = currentTvShow.synopsis

            Glide.with(root)
                .load(
                    root.context.resources.getIdentifier(
                        currentTvShow.posterName, "drawable",
                        root.context.packageName
                    )
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPosterBgDetail)

            Glide.with(root)
                .load(
                    root.context.resources.getIdentifier(
                        currentTvShow.posterName, "drawable",
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

    override fun onClick(tvShow: TVShow) {
        with(Intent(requireActivity(), DetailActivity::class.java)) {
            putExtra(DetailActivity.EXTRA_CONTENT_TV_SHOW, tvShow)
            putExtra(DetailActivity.GOTO_TV_SHOW_DETAIL, true)

            requireActivity().startActivity(this)
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