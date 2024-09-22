package com.example.filters

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import kotlin.math.min

object GeneralUtils {
    fun generateCircularBitmap(input: Bitmap?): Bitmap {
        val width = input!!.width
        val height = input.height
        val outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val path = Path()
        path.addCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            min(width.toDouble(), (height / 2).toDouble()).toFloat(),
            Path.Direction.CCW
        )

        val canvas = Canvas(outputBitmap)
        canvas.clipPath(path)
        canvas.drawBitmap(input, 0f, 0f, null)
        return outputBitmap
    }
}
