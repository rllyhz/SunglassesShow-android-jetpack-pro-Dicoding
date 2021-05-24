package id.rllyhz.sunglassesshow.ui.favorites.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rllyhz.sunglassesshow.R
import id.rllyhz.sunglassesshow.api.ApiEndpoint.Companion.IMAGE_URL
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow
import id.rllyhz.sunglassesshow.databinding.ItemTvshowListBinding

class FavTVShowListAdapter :
    PagedListAdapter<FavTVShow, FavTVShowListAdapter.FavTVShowListViewHolder>(FavTVShowComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTVShowListViewHolder {
        val binding =
            ItemTvshowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTVShowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavTVShowListViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.bind(this)
        }
    }

    fun get(position: Int): FavTVShow =
        getItem(position) ?: FavTVShow(0, "", "", "", 0, 0f)

    inner class FavTVShowListViewHolder(
        private val binding: ItemTvshowListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favTVShow: FavTVShow) {
            binding.apply {
                with(ivItemTvshowList) {
                    Glide.with(context)
                        .load(IMAGE_URL + favTVShow.posterPath)
                        .placeholder(R.drawable.bg_poster_placeholder)
                        .error(R.drawable.bg_poster_error)
                        .into(this)

                    contentDescription = favTVShow.title
                }

                rbTvshowList.rating = favTVShow.rating
                tvTvshowListTitle.text = itemView.context.getString(
                    R.string.title_format,
                    favTVShow.title,
                    favTVShow.year.toString()
                )

                itemView.setOnClickListener { favTVShowItemCallback?.onClick(favTVShow) }
            }
        }
    }

    class FavTVShowComparator : DiffUtil.ItemCallback<FavTVShow>() {
        override fun areItemsTheSame(oldItem: FavTVShow, newItem: FavTVShow): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FavTVShow, newItem: FavTVShow): Boolean =
            oldItem == newItem
    }

    interface FavTVShowItemCallback {
        fun onClick(favTVShow: FavTVShow)
    }

    private var favTVShowItemCallback: FavTVShowItemCallback? = null

    fun setItemCallback(callback: FavTVShowItemCallback) {
        favTVShowItemCallback = callback
    }
}