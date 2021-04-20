package id.rllyhz.sunglassesshow.ui.features.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.databinding.FragmentMovieListBinding
import id.rllyhz.sunglassesshow.ui.main.MainViewModel

class MovieListFragment : Fragment(), MovieListAdapter.MovieItemCallback {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!! // using the official documentation approach

    private val viewModel: MainViewModel by viewModels()
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

        movieListAdapter = MovieListAdapter()
        setupUI()
    }

    private fun setupUI() {
        movieListAdapter.submitList(viewModel.getMovies().toMutableList())

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
        //
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG: String = MovieListFragment::class.java.simpleName
        fun newInstance(): MovieListFragment = MovieListFragment()
    }
}