package id.rllyhz.sunglassesshow.ui.features.tvshow.list

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
import id.rllyhz.sunglassesshow.databinding.ItemTvshowListBinding
import java.util.*
import kotlin.collections.ArrayList

class TVShowListAdapter(
    private val fragment: TVShowListFragment
) :
    ListAdapter<TVShow, TVShowListAdapter.TVShowListViewHolder>(TVShowComparator()) {
    private var originalList: List<TVShow>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowListViewHolder {
        val binding =
            ItemTvshowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVShowListViewHolder, position: Int) {
        getItem(position).apply {
            holder.bind(this)
        }
    }

    fun initData(list: List<TVShow>?) {
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
                val filteredList = ArrayList<TVShow>()

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

    inner class TVShowListViewHolder(
        private val binding: ItemTvshowListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TVShow) {
            binding.apply {
                with(ivItemTvshowList) {
                    Glide.with(this)
                        .load(IMAGE_URL + tvShow.posterPath)
                        .placeholder(R.drawable.bg_poster_placeholder)
                        .error(R.drawable.bg_poster_error)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(this)

                    contentDescription = tvShow.title
                }

                rbTvshowList.rating = tvShow.rating
                tvTvshowListTitle.text = itemView.resources.getString(
                    R.string.title_format,
                    tvShow.title,
                    tvShow.year.toString()
                )

                root.setOnClickListener { tvShowItemCallback?.onClick(tvShow) }
            }
        }
    }

    // tvshow comparator
    class TVShowComparator : DiffUtil.ItemCallback<TVShow>() {
        override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
            oldItem == newItem
    }


    // onClick callback
    interface TVShowItemCallback {
        fun onClick(tvShow: TVShow)
    }

    private var tvShowItemCallback: TVShowItemCallback? = null

    fun setItemCallback(callback: TVShowItemCallback) {
        tvShowItemCallback = callback
    }
}