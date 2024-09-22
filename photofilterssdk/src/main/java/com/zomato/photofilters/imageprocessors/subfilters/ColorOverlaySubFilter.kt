package com.zomato.photofilters.imageprocessors.subfilters

import android.graphics.Bitmap
import com.zomato.photofilters.imageprocessors.ImageProcessor
import com.zomato.photofilters.imageprocessors.SubFilter

class ColorOverlaySubFilter(
    private val colorOverlayDepth: Int,
    private val colorOverlayRed: Float,
    private val colorOverlayGreen: Float,
    private val colorOverlayBlue: Float
) : SubFilter {
    override fun process(inputImage: Bitmap?): Bitmap {
        return ImageProcessor.doColorOverlay(
            colorOverlayDepth, colorOverlayRed, colorOverlayGreen, colorOverlayBlue, inputImage
        )
    }

    override var tag: Any
        get() = Companion.tag
        set(tag) {
            Companion.tag = tag as String
        }

    companion object {
        private var tag = ""
    }
}
