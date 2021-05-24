package id.rllyhz.sunglassesshow.ui.features.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.api.ApiEndpoint.Companion.IMAGE_URL
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.databinding.ItemMovieListBinding
import java.util.*
import kotlin.collections.ArrayList

class MovieListAdapter(
    private val fragment: MovieListFragment
) :
    ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(MovieComparator()) {
    private var originalList: List<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position).apply {
            holder.bind(this)
        }
    }

    fun initData(list: List<Movie>?) {
        originalList = list
        submitList(originalList)

        if (list == null || list.isEmpty())
            setNoResultsUI(true)
        else
            setNoResultsUI(false)
    }

    fun search(query: String) {
        if (query.isNotEmpty()) {
            originalList?.let {
                val filteredList = ArrayList<Movie>()

                for (item in it) {
                    if (item.title.toLowerCase(Locale.ROOT)
                            .contains(query.toLowerCase(Locale.ROOT))
                    ) {
                        filteredList.add(item)
                    }
                }

                if (filteredList.isNotEmpty()) {
                    submitList(filteredList)
                    setNoResultsUI(false)
                } else {
                    setNoResultsUI(true)
                }
            }
        } else {
            submitList(originalList)
            setNoResultsUI(false)
        }
    }

    private fun setNoResultsUI(state: Boolean) {
        fragment.setNoResultsUI(state)
    }

    inner class MovieListViewHolder(
        private val binding: ItemMovieListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                with(ivItemMovieList) {
                    Glide.with(this)
                        .load(IMAGE_URL + movie.posterPath)
                        .placeholder(R.drawable.bg_poster_placeholder)
                        .error(R.drawable.bg_poster_error)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(this)
                }

                rbMovieList.rating = movie.rating
                tvMovieListTitle.text = itemView.context.resources.getString(
                    R.string.title_format,
                    movie.title,
                    movie.year.toString()
                )

                itemView.setOnClickListener { movieItemCallback?.onClick(movie) }
            }
        }
    }

    // movie comparator
    class MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }


    // onClick callback
    interface MovieItemCallback {
        fun onClick(movie: Movie)
    }

    private var movieItemCallback: MovieItemCallback? = null

    fun setItemCallback(callback: MovieItemCallback) {
        movieItemCallback = callback
    }
}