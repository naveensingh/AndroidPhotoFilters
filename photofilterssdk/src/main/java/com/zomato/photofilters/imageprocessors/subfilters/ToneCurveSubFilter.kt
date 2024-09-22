package com.zomato.photofilters.imageprocessors.subfilters

import android.graphics.Bitmap
import com.zomato.photofilters.geometry.BezierSpline
import com.zomato.photofilters.geometry.Point
import com.zomato.photofilters.imageprocessors.ImageProcessor
import com.zomato.photofilters.imageprocessors.SubFilter

class ToneCurveSubFilter(
    rgbKnots: Array<Point?>?,
    redKnots: Array<Point?>?,
    greenKnots: Array<Point?>?,
    blueKnots: Array<Point?>?
) : SubFilter {
    // These are knots which contains the plot points
    private var rgbKnots: Array<Point?>?
    private var greenKnots: Array<Point?>?
    private var redKnots: Array<Point?>?
    private var blueKnots: Array<Point?>?
    private var rgb: IntArray? = null
    private var r: IntArray? = null
    private var g: IntArray? = null
    private var b: IntArray? = null

    /**
     * Initialise ToneCurveSubfilter (NOTE : Don't pass null knots, pass straight line instead)
     * Knots are the points in 2D taken by tweaking photoshop channels(plane ranging from 0-255)
     *
     * @param rgbKnots   rgb Knots
     * @param redKnots   Knots in Red Channel
     * @param greenKnots Knots in green Channel
     * @param blueKnots  Knots in Blue channel
     */
    init {
        val straightKnots = arrayOfNulls<Point>(2)
        straightKnots[0] = Point(0f, 0f)
        straightKnots[1] = Point(255f, 255f)
        if (rgbKnots == null) {
            this.rgbKnots = straightKnots
        } else {
            this.rgbKnots = rgbKnots
        }
        if (redKnots == null) {
            this.redKnots = straightKnots
        } else {
            this.redKnots = redKnots
        }
        if (greenKnots == null) {
            this.greenKnots = straightKnots
        } else {
            this.greenKnots = greenKnots
        }
        if (blueKnots == null) {
            this.blueKnots = straightKnots
        } else {
            this.blueKnots = blueKnots
        }
    }

    override fun process(inputImage: Bitmap?): Bitmap {
        rgbKnots = sortPointsOnXAxis(rgbKnots)
        redKnots = sortPointsOnXAxis(redKnots)
        greenKnots = sortPointsOnXAxis(greenKnots)
        blueKnots = sortPointsOnXAxis(blueKnots)
        if (rgb == null) {
            rgb = BezierSpline.curveGenerator(rgbKnots)
        }

        if (r == null) {
            r = BezierSpline.curveGenerator(redKnots)
        }

        if (g == null) {
            g = BezierSpline.curveGenerator(greenKnots)
        }

        if (b == null) {
            b = BezierSpline.curveGenerator(blueKnots)
        }

        return ImageProcessor.applyCurves(rgb, r, g, b, inputImage)
    }

    fun sortPointsOnXAxis(points: Array<Point?>?): Array<Point?>? {
        if (points == null) {
            return null
        }
        for (s in 1 until points.size - 1) {
            for (k in 0..points.size - 2) {
                if (points[k]!!.x > points[k + 1]!!.x) {
                    var temp = 0f
                    temp = points[k]!!.x
                    points[k]!!.x = points[k + 1]!!.x //swapping values
                    points[k + 1]!!.x = temp
                }
            }
        }
        return points
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
