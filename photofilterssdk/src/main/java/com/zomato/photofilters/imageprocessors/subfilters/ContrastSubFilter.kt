package com.zomato.photofilters.imageprocessors.subfilters

import android.graphics.Bitmap
import com.zomato.photofilters.imageprocessors.ImageProcessor
import com.zomato.photofilters.imageprocessors.SubFilter

class ContrastSubFilter(contrast: Float) : SubFilter {
    var contrast: Float = 0f

    init {
        this.contrast = contrast
    }

    override fun process(inputImage: Bitmap?): Bitmap {
        return ImageProcessor.doContrast(contrast, inputImage)
    }

    override var tag: Any
        get() = Companion.tag
        set(tag) {
            Companion.tag = tag as String
        }

    fun changeContrast(value: Float) {
        this.contrast += value
    }

    companion object {
        private var tag = ""
    }
}
