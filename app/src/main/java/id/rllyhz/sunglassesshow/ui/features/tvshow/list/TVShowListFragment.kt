package id.rllyhz.sunglassesshow.ui.features.tvshow.list

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.databinding.FragmentTvshowListBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.main.MainActivity
import id.rllyhz.sunglassesshow.ui.main.MainViewModel
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.ViewModelFactory

class TVShowListFragment : Fragment(), TVShowListAdapter.TVShowItemCallback {
    private lateinit var _binding: FragmentTvshowListBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private var _activity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance(requireContext().applicationContext as Application)
        )[MainViewModel::class.java]

        tvShowListAdapter = TVShowListAdapter(this)
        tvShowListAdapter.setItemCallback(this)

        setupUI()

        viewModel.tvShows.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    _activity?.onLoading(false)
                    tvShowListAdapter.initData(resource.data)
                }
                is Resource.Error -> {
                    _activity?.onLoading(false)
                }
                is Resource.Loading -> {
                    _activity?.onLoading(true)
                }
                else -> Unit
            }
        }

        viewModel.getAllTVShows()
    }

    private fun setupUI() {
        _activity?.onLoading(true)

        _binding.apply {
            with(rvTvshowList) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowListAdapter
            }

            searchViewTvShowsList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = false

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null)
                        tvShowListAdapter.search(newText)

                    return true
                }
            })
        }
    }

    fun setNoResultsUI(state: Boolean) {
        with(_binding) {
            when (state) {
                true -> {
                    rvTvshowList.visibility = View.GONE
                    tvFeedback.visibility = View.VISIBLE
                }
                false -> {
                    rvTvshowList.visibility = View.VISIBLE
                    tvFeedback.visibility = View.GONE
                }
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _activity = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        _activity = null
    }

    companion object {
        fun newInstance(): TVShowListFragment = TVShowListFragment()
    }
}