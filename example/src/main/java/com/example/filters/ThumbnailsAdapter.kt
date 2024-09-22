package com.example.filters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ThumbnailsAdapter(
    private val dataSet: List<ThumbnailItem>,
    private val thumbnailCallback: ThumbnailCallback?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        Log.v(TAG, "Thumbnails Adapter has " + dataSet.size + " items")
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        Log.v(TAG, "On Create View Holder Called")
        val itemView: View = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.list_thumbnail_item, viewGroup, false
        )

        return ThumbnailsViewHolder(itemView)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thumbnailItem = dataSet[position]
        Log.v(TAG, "On Bind View Called")
        val thumbnailsViewHolder = holder as ThumbnailsViewHolder
        thumbnailsViewHolder.title.text = thumbnailItem.filter.name
        thumbnailsViewHolder.thumbnail.setImageBitmap(thumbnailItem.image)
        thumbnailsViewHolder.thumbnail.scaleType = ImageView.ScaleType.FIT_START
        setAnimation(thumbnailsViewHolder.thumbnail, position)
        thumbnailsViewHolder.thumbnail.setOnClickListener {
            if (lastPosition != position) {
                thumbnailCallback?.onThumbnailClick(thumbnailItem.filter)
                lastPosition = holder.adapterPosition
            }
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        viewToAnimate.alpha = 0f
        viewToAnimate.animate().alpha(1f).setDuration(250).start()
        lastPosition = position
    }

    class ThumbnailsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.filter_name)
        var thumbnail: ImageView = v.findViewById(R.id.thumbnail)
    }

    companion object {
        private const val TAG = "THUMBNAILS_ADAPTER"
        private var lastPosition = -1
    }
}
