package com.example.filters

import android.content.Context
import android.graphics.Bitmap

object ThumbnailsManager {
    private var filterThumbs: MutableList<ThumbnailItem> = ArrayList(10)
    private var processedThumbs: MutableList<ThumbnailItem> = ArrayList(10)

    fun addThumb(thumbnailItem: ThumbnailItem) {
        filterThumbs.add(thumbnailItem)
    }

    fun processThumbs(context: Context): List<ThumbnailItem> {
        for (thumb in filterThumbs) {
            // scaling down the image
            val size = context.resources.getDimension(R.dimen.thumbnail_size)
            thumb.image = Bitmap.createScaledBitmap(
                thumb.image!!, size.toInt(), size.toInt(), false
            )
            thumb.image = thumb.filter.processFilter(thumb.image)
            //cropping circle
            thumb.image = GeneralUtils.generateCircularBitmap(thumb.image)
            processedThumbs.add(thumb)
        }
        return processedThumbs
    }

    fun clearThumbs() {
        filterThumbs = ArrayList()
        processedThumbs = ArrayList()
    }
}
