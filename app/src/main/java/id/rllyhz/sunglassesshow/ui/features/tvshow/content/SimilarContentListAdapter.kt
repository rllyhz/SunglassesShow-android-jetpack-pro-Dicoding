package id.rllyhz.sunglassesshow.ui.features.tvshow.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.api.ApiEndpoint.Companion.IMAGE_URL
import id.rllyhz.sunglassesshow.data.TVShow
import id.rllyhz.sunglassesshow.databinding.ItemSimilarContentBinding

class SimilarContentListAdapter :
    ListAdapter<TVShow, SimilarContentListAdapter.SimilarContentViewHolder>(SimilarContentComparator()) {

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

        fun bind(tvShows: TVShow) {
            with(binding) {
                Glide.with(itemView)
                    .load(IMAGE_URL + tvShows.posterPath)
                    .error(R.drawable.bg_poster_error)
                    .placeholder(R.drawable.bg_poster_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivItemSimilar)

                ivItemSimilar.contentDescription = tvShows.title

                itemView.setOnClickListener { itemCallback?.onClick(tvShows) }
            }
        }
    }

    class SimilarContentComparator : DiffUtil.ItemCallback<TVShow>() {
        override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
            oldItem == newItem
    }

    interface SimilarContentItemCallback {
        fun onClick(tvShow: TVShow)
    }

    private var itemCallback: SimilarContentItemCallback? = null

    fun setItemCallback(callback: SimilarContentItemCallback) {
        itemCallback = callback
    }
}