package com.zomato.photofilters.imageprocessors

import android.graphics.Bitmap
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter
import com.zomato.photofilters.imageprocessors.subfilters.ToneCurveSubFilter
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubFilter

/**
 * This Class represents a ImageFilter and includes many subfilters within, we add different subfilters to this class's
 * object and they are then processed in that particular order
 */
class Filter {
    private var subFilters: MutableList<SubFilter>? = ArrayList()
    var name: String? = null

    constructor(filter: Filter) {
        this.subFilters = filter.subFilters
    }

    constructor()

    constructor(name: String?) {
        this.name = name
    }

    /**
     * Adds a Subfilter to the Main Filter
     *
     * @param subFilter Subfilter like contrast, brightness, tone Curve etc. subfilter
     * @see BrightnessSubFilter
     *
     * @see ColorOverlaySubFilter
     *
     * @see ContrastSubFilter
     *
     * @see ToneCurveSubFilter
     *
     * @see VignetteSubFilter
     *
     * @see SaturationSubFilter
     */
    fun addSubFilter(subFilter: SubFilter) {
        subFilters!!.add(subFilter)
    }

    /**
     * Adds all [SubFilter]s from the List to the Main Filter.
     * @param subFilterList a list of [SubFilter]s; must not be null
     */
    fun addSubFilters(subFilterList: List<SubFilter>?) {
        subFilters!!.addAll(subFilterList!!)
    }

    /**
     * Get a new list of currently applied subfilters.
     *
     * @return a [List] of [SubFilter].
     * Empty if no filters are added to [.subFilters];
     * never null
     *
     * @see .addSubFilter
     * @see .addSubFilters
     */
    fun getSubFilters(): List<SubFilter> {
        if (subFilters == null || subFilters!!.isEmpty()) return ArrayList(0)
        return ArrayList(subFilters)
    }

    /**
     * Clears all the subfilters from the Parent Filter
     */
    fun clearSubFilters() {
        subFilters!!.clear()
    }

    /**
     * Removes the subfilter containing Tag from the Parent Filter
     */
    fun removeSubFilterWithTag(tag: String) {
        val iterator = subFilters!!.iterator()
        while (iterator.hasNext()) {
            val subFilter = iterator.next()
            if (subFilter.tag == tag) {
                iterator.remove()
            }
        }
    }

    /**
     * Returns The filter containing Tag
     */
    fun getSubFilterByTag(tag: String): SubFilter? {
        for (subFilter in subFilters!!) {
            if (subFilter.tag == tag) {
                return subFilter
            }
        }
        return null
    }

    /**
     * Give the output Bitmap by applying the defined filter
     *
     * @param inputImage Input Bitmap on which filter is to be applied
     * @return filtered Bitmap
     */
    fun processFilter(inputImage: Bitmap?): Bitmap? {
        var outputImage = inputImage
        if (outputImage != null) {
            for (subFilter in subFilters!!) {
                try {
                    outputImage = subFilter.process(outputImage)
                } catch (oe: OutOfMemoryError) {
                    System.gc()
                    try {
                        outputImage = subFilter.process(outputImage)
                    } catch (ignored: OutOfMemoryError) {
                    }
                }
            }
        }

        return outputImage
    }
}
