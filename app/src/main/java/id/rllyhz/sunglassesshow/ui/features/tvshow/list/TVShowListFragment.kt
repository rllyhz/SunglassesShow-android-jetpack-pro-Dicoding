package id.rllyhz.sunglassesshow.ui.features.tvshow.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.databinding.FragmentTvshowListBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.main.MainViewModel
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.ViewModelFactory

class TVShowListFragment : Fragment(), TVShowListAdapter.TVShowItemCallback {
    private var _binding: FragmentTvshowListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
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

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance()
        )[MainViewModel::class.java]

        tvShowListAdapter = TVShowListAdapter()
        tvShowListAdapter.setItemCallback(this)

        setupUI()

        lifecycleScope.launchWhenStarted {
            viewModel.getTVShowsTest().observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        tvShowListAdapter.submitList(resource.data)
                    }
                    is Resource.Error -> {
                        Log.d("Test", "Error nih")
                    }
                    is Resource.Loading -> {
                        Log.d("Test", "Loading")
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupUI() {
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
}