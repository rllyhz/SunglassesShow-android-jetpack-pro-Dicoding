package id.rllyhz.sunglassesshow.ui.features.movie.list

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.databinding.FragmentMovieListBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.main.MainActivity
import id.rllyhz.sunglassesshow.ui.main.MainViewModel
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.ViewModelFactory

class MovieListFragment : Fragment(), MovieListAdapter.MovieItemCallback {
    private lateinit var _binding: FragmentMovieListBinding

    private lateinit var viewModel: MainViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private var _activity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance(requireContext().applicationContext as Application)
        )[MainViewModel::class.java]

        movieListAdapter = MovieListAdapter()
        movieListAdapter.setItemCallback(this)

        setupUI()

        viewModel.movies.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    _activity?.onLoading(false)
                    movieListAdapter.submitList(resource.data)
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

        viewModel.initAllMovies()
    }

    private fun setupUI() {
        _activity?.onLoading(true)

        _binding.apply {

            with(rvMovieList) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieListAdapter
            }
        }
    }

    override fun onClick(movie: Movie) {
        with(Intent(requireActivity(), DetailActivity::class.java)) {
            putExtra(DetailActivity.EXTRA_CONTENT_MOVIE, movie)
            putExtra(DetailActivity.GOTO_MOVIE_DETAIL, true)

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
        fun newInstance(): MovieListFragment = MovieListFragment()
    }
}