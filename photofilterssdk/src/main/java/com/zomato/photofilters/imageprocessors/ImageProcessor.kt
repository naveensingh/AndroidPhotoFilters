package com.zomato.photofilters.imageprocessors

import android.graphics.Bitmap

object ImageProcessor {
    fun applyCurves(
        rgb: IntArray?,
        red: IntArray?,
        green: IntArray?,
        blue: IntArray?,
        inputImage: Bitmap?
    ): Bitmap {
        // create output bitmap
        val outputImage = inputImage

        // get image size
        val width = inputImage!!.width
        val height = inputImage.height

        var pixels: IntArray? = IntArray(width * height)
        outputImage!!.getPixels(pixels!!, 0, width, 0, 0, width, height)

        if (rgb != null) {
            pixels = NativeImageProcessor.applyRGBCurve(pixels, rgb, width, height)
        }

        if (!(red == null && green == null && blue == null)) {
            pixels =
                NativeImageProcessor.applyChannelCurves(pixels, red, green, blue, width, height)
        }

        try {
            outputImage.setPixels(pixels!!, 0, width, 0, 0, width, height)
        } catch (ise: IllegalStateException) {
        }
        return outputImage
    }

    fun doBrightness(value: Int, inputImage: Bitmap?): Bitmap {
        val width = inputImage!!.width
        val height = inputImage.height
        val pixels = IntArray(width * height)

        inputImage.getPixels(pixels, 0, width, 0, 0, width, height)
        NativeImageProcessor.doBrightness(pixels, value, width, height)
        inputImage.setPixels(pixels, 0, width, 0, 0, width, height)

        return inputImage
    }

    fun doContrast(value: Float, inputImage: Bitmap?): Bitmap {
        val width = inputImage!!.width
        val height = inputImage.height
        val pixels = IntArray(width * height)

        inputImage.getPixels(pixels, 0, width, 0, 0, width, height)
        NativeImageProcessor.doContrast(pixels, value, width, height)
        inputImage.setPixels(pixels, 0, width, 0, 0, width, height)

        return inputImage
    }


    fun doColorOverlay(
        depth: Int,
        red: Float,
        green: Float,
        blue: Float,
        inputImage: Bitmap?
    ): Bitmap {
        val width = inputImage!!.width
        val height = inputImage.height
        val pixels = IntArray(width * height)

        inputImage.getPixels(pixels, 0, width, 0, 0, width, height)
        NativeImageProcessor.doColorOverlay(pixels, depth, red, green, blue, width, height)
        inputImage.setPixels(pixels, 0, width, 0, 0, width, height)

        return inputImage
    }

    fun doSaturation(inputImage: Bitmap?, level: Float): Bitmap {
        val width = inputImage!!.width
        val height = inputImage.height
        val pixels = IntArray(width * height)

        inputImage.getPixels(pixels, 0, width, 0, 0, width, height)
        NativeImageProcessor.doSaturation(pixels, level, width, height)
        inputImage.setPixels(pixels, 0, width, 0, 0, width, height)
        return inputImage
    }
}
