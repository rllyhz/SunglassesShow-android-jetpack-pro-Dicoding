package id.rllyhz.sunglassesshow.ui.favorites.tvshow

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.databinding.FragmentFavTvshowListBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.favorites.FavoritesActivity
import id.rllyhz.sunglassesshow.ui.favorites.FavoritesViewModel
import id.rllyhz.sunglassesshow.ui.favorites.SwipeItemCallback
import id.rllyhz.sunglassesshow.utils.ViewModelFactory
import id.rllyhz.sunglassesshow.utils.asModel

class FavTVShowListFragment : Fragment(), FavTVShowListAdapter.FavTVShowItemCallback {
    private var _binding: FragmentFavTvshowListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favTVShowListAdapter: FavTVShowListAdapter
    private var _activity: FavoritesActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavTvshowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance(requireContext().applicationContext as Application)
        )[FavoritesViewModel::class.java]

        favTVShowListAdapter = FavTVShowListAdapter()
        favTVShowListAdapter.setItemCallback(this)

        val swipedItemCallback = object : SwipeItemCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val favTVShow = favTVShowListAdapter.get(viewHolder.adapterPosition)
                viewModel.deleteFavTVShow(favTVShow)
                favTVShowListAdapter.currentList?.dataSource?.invalidate()

                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.favorites_deleted_tv_show_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        ItemTouchHelper(swipedItemCallback).attachToRecyclerView(binding.rvFavTvshowList)

        setupUI()

        viewModel.favTVShows().observe(viewLifecycleOwner) {
            _activity?.onLoading(false)
            favTVShowListAdapter.submitList(it)

            with(binding) {
                if (it.size != 0) {
                    tvFeedback.visibility = View.GONE
                    rvFavTvshowList.visibility = View.VISIBLE
                } else {
                    tvFeedback.visibility = View.VISIBLE
                    rvFavTvshowList.visibility = View.GONE
                }
            }
        }
    }

    private fun setupUI() {
        _activity?.onLoading(true)

        binding.apply {
            tvFeedback.visibility = View.GONE

            with(rvFavTvshowList) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = favTVShowListAdapter
            }
        }
    }

    override fun onClick(favTVShow: FavTVShow) {
        with(Intent(requireActivity(), DetailActivity::class.java)) {
            putExtra(DetailActivity.EXTRA_CONTENT_TV_SHOW, favTVShow.asModel())
            putExtra(DetailActivity.GOTO_MOVIE_DETAIL, false)

            requireActivity().startActivity(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _activity = context as FavoritesActivity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        _activity = null
    }


    companion object {
        fun newInstance(): FavTVShowListFragment = FavTVShowListFragment()
    }
}