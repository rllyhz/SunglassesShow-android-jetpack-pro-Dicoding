package id.rllyhz.sunglassesshow.ui.favorites.movie

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
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.databinding.FragmentFavMovieListBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.favorites.FavoritesActivity
import id.rllyhz.sunglassesshow.ui.favorites.FavoritesViewModel
import id.rllyhz.sunglassesshow.ui.favorites.SwipeItemCallback
import id.rllyhz.sunglassesshow.utils.ViewModelFactory
import id.rllyhz.sunglassesshow.utils.asModel

class FavMovieListFragment : Fragment(), FavMovieListAdapter.FavMovieItemCallback {
    private var _binding: FragmentFavMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favMovieListAdapter: FavMovieListAdapter
    private var _activity: FavoritesActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance(requireContext().applicationContext as Application)
        )[FavoritesViewModel::class.java]

        favMovieListAdapter = FavMovieListAdapter()
        favMovieListAdapter.setItemCallback(this)

        val swipedItemCallback = object :
            SwipeItemCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val favMovie = favMovieListAdapter.get(viewHolder.adapterPosition)
                viewModel.deleteFavMovie(favMovie)
                favMovieListAdapter.currentList?.dataSource?.invalidate()

                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.favorites_deleted_movie_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        ItemTouchHelper(swipedItemCallback).attachToRecyclerView(binding.rvFavMovieList)

        setupUI()

        viewModel.favMovies().observe(viewLifecycleOwner) {
            _activity?.onLoading(false)
            favMovieListAdapter.submitList(it)

            with(binding) {
                if (it.size != 0) {
                    tvFeedback.visibility = View.GONE
                    rvFavMovieList.visibility = View.VISIBLE
                } else {
                    tvFeedback.visibility = View.VISIBLE
                    rvFavMovieList.visibility = View.GONE
                }
            }
        }
    }

    private fun setupUI() {
        _activity?.onLoading(true)

        binding.apply {
            tvFeedback.visibility = View.GONE

            with(rvFavMovieList) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = favMovieListAdapter
            }
        }
    }

    override fun onClick(favMovie: FavMovie) {
        with(Intent(requireActivity(), DetailActivity::class.java)) {
            putExtra(DetailActivity.EXTRA_CONTENT_MOVIE, favMovie.asModel())
            putExtra(DetailActivity.GOTO_MOVIE_DETAIL, true)

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
        fun newInstance(): FavMovieListFragment = FavMovieListFragment()
    }
}