package id.rllyhz.sunglassesshow.ui.features.movie.content

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
import id.rllyhz.sunglassesshow.databinding.ItemSimilarContentBinding

class SimilarContentListAdapter :
    ListAdapter<Movie, SimilarContentListAdapter.SimilarContentViewHolder>(SimilarContentComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarContentViewHolder {
        val binding =
            ItemSimilarContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarContentViewHolder, position: Int) {
        getItem(position).apply {
            holder.bind(this)
        }
    }

    inner class SimilarContentViewHolder(
        private val binding: ItemSimilarContentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView)
                    .load(IMAGE_URL + movie.posterPath)
                    .error(R.drawable.bg_poster_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivItemSimilar)

                itemView.setOnClickListener { itemCallback?.onClick(movie) }
            }
        }
    }

    class SimilarContentComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    interface SimilarContentItemCallback {
        fun onClick(movie: Movie)
    }

    private var itemCallback: SimilarContentItemCallback? = null

    fun setItemCallback(callback: SimilarContentItemCallback) {
        itemCallback = callback
    }
}