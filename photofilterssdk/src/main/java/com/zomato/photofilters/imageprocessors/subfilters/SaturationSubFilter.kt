package com.zomato.photofilters.imageprocessors.subfilters

import android.graphics.Bitmap
import com.zomato.photofilters.imageprocessors.ImageProcessor
import com.zomato.photofilters.imageprocessors.SubFilter

class SaturationSubFilter(
    var saturation: Float
) : SubFilter {
    override fun process(inputImage: Bitmap?): Bitmap {
        return ImageProcessor.doSaturation(inputImage, saturation)
    }

    override var tag: Any
        get() = Companion.tag
        set(tag) {
            Companion.tag = tag as String
        }

    fun setLevel(level: Float) {
        this.saturation = level
    }

    companion object {
        private var tag = ""
    }
}
