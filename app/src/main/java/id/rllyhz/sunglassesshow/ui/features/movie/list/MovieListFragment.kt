package id.rllyhz.sunglassesshow.ui.features.movie.list

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
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.databinding.FragmentMovieListBinding
import id.rllyhz.sunglassesshow.ui.detail.DetailActivity
import id.rllyhz.sunglassesshow.ui.main.MainViewModel
import id.rllyhz.sunglassesshow.utils.Resource
import id.rllyhz.sunglassesshow.utils.ViewModelFactory

class MovieListFragment : Fragment(), MovieListAdapter.MovieItemCallback {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!! // using the official documentation approach

    private lateinit var viewModel: MainViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance()
        )[MainViewModel::class.java]

        movieListAdapter = MovieListAdapter()
        movieListAdapter.setItemCallback(this)

        setupUI()

        lifecycleScope.launchWhenStarted {
            viewModel.getMoviesTest().observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        movieListAdapter.submitList(resource.data)
                    }
                    is Resource.Error -> {
                        resource.message?.let { Log.d("Test", it) }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): MovieListFragment = MovieListFragment()
    }
}