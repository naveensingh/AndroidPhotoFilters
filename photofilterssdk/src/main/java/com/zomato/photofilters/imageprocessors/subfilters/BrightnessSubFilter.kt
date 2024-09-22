package com.zomato.photofilters.imageprocessors.subfilters

import android.graphics.Bitmap
import com.zomato.photofilters.imageprocessors.ImageProcessor
import com.zomato.photofilters.imageprocessors.SubFilter

class BrightnessSubFilter(brightness: Int) : SubFilter {
    private var brightness: Int = 0

    init {
        this.brightness = brightness
    }

    override fun process(inputImage: Bitmap?): Bitmap {
        return ImageProcessor.doBrightness(brightness, inputImage)
    }

    override var tag: Any
        get() = Companion.tag
        set(tag) {
            Companion.tag = tag as String
        }

    fun changeBrightness(value: Int) {
        this.brightness += value
    }

    companion object {
        private var tag = ""
    }
}
