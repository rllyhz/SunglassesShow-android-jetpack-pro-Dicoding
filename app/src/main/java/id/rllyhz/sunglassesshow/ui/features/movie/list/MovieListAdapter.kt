package id.rllyhz.sunglassesshow.ui.features.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.databinding.ItemMovieListBinding

class MovieListAdapter :
    ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(MovieComparator()) {

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

    inner class MovieListViewHolder(
        private val binding: ItemMovieListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                with(ivItemMovieList) {
                    Glide.with(this)
                        .load(
                            context.resources.getIdentifier(
                                movie.posterName, "drawable",
                                context.packageName
                            )
                        )
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(this)
                }

                rbMovieList.rating = 3.4f
                tvMovieListTitle.text = movie.title
                root.setOnClickListener { movieItemCallback?.onClick(movie) }
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