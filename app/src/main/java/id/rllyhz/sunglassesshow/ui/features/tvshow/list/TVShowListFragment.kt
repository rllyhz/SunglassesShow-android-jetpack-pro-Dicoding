package id.rllyhz.sunglassesshow.ui.features.tvshow.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.databinding.FragmentTvshowListBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.main.MainViewModel

class TVShowListFragment : Fragment(), TVShowListAdapter.TVShowItemCallback {
    private var _binding: FragmentTvshowListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var tvShowListAdapter: TVShowListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowListAdapter = TVShowListAdapter()
        tvShowListAdapter.setItemCallback(this)
        setupUI()
    }

    private fun setupUI() {
        tvShowListAdapter.submitList(viewModel.getTVShows().toMutableList())

        binding.apply {
            progressbar.visibility = View.GONE

            with(rvTvshowList) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowListAdapter
            }
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
        val TAG: String = TVShowListFragment::class.java.simpleName
        fun newInstance(): TVShowListFragment = TVShowListFragment()
    }
}