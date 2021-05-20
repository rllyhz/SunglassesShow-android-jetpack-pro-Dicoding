package id.rllyhz.sunglassesshow.ui.favorites.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.api.ApiEndpoint.Companion.IMAGE_URL
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.databinding.ItemMovieListBinding

class FavMovieListAdapter :
    PagedListAdapter<FavMovie, FavMovieListAdapter.FavMovieListViewHolder>(FavMovieComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieListViewHolder {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavMovieListViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.bind(this)
        }
    }

    fun get(position: Int): FavMovie =
        getItem(position) ?: FavMovie(0, "", "", "", 0, 0f)

    inner class FavMovieListViewHolder(
        private val binding: ItemMovieListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favMovie: FavMovie) {
            binding.apply {
                with(ivItemMovieList) {
                    Glide.with(context)
                        .load(IMAGE_URL + favMovie.posterPath)
                        .placeholder(R.drawable.bg_poster_placeholder)
                        .error(R.drawable.bg_poster_error)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(this)
                }

                rbMovieList.rating = favMovie.rating
                tvMovieListTitle.text = itemView.context.getString(
                    R.string.title_format,
                    favMovie.title,
                    favMovie.year.toString()
                )

                itemView.setOnClickListener { favMovieItemCallback?.onClick(favMovie) }
            }
        }
    }

    class FavMovieComparator : DiffUtil.ItemCallback<FavMovie>() {
        override fun areItemsTheSame(oldItem: FavMovie, newItem: FavMovie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FavMovie, newItem: FavMovie): Boolean =
            oldItem == newItem
    }

    interface FavMovieItemCallback {
        fun onClick(favMovie: FavMovie)
    }

    private var favMovieItemCallback: FavMovieItemCallback? = null

    fun setItemCallback(callback: FavMovieItemCallback) {
        favMovieItemCallback = callback
    }
}