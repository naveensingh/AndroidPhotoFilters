package com.zomato.photofilters.imageprocessors.subfilters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.zomato.photofilters.R
import com.zomato.photofilters.imageprocessors.SubFilter

class VignetteSubFilter(private val context: Context, alpha: Int) : SubFilter {
    private var alpha = 0

    init {
        this.alpha = alpha
    }

    override fun process(inputImage: Bitmap?): Bitmap {
        var vignette = BitmapFactory.decodeResource(context.resources, R.drawable.vignette)

        vignette =
            Bitmap.createScaledBitmap(vignette!!, inputImage!!.width, inputImage.height, true)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.alpha = alpha

        val comboImage = Canvas(inputImage)
        comboImage.drawBitmap(vignette, 0f, 0f, paint)

        return inputImage
    }

    override var tag: Any
        get() = Companion.tag
        set(tag) {
            Companion.tag = tag as String
        }

    fun setAlpha(alpha: Int) {
        this.alpha = alpha
    }

    fun changeAlpha(value: Int) {
        this.alpha += value
        if (alpha > 255) {
            alpha = 255
        } else if (alpha < 0) {
            alpha = 0
        }
    }

    companion object {
        private var tag = ""
    }
}
