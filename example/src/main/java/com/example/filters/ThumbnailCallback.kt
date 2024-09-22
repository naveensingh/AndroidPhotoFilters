package com.example.filters

import com.zomato.photofilters.imageprocessors.Filter

interface ThumbnailCallback {
    fun onThumbnailClick(filter: Filter?)
}
