package com.example.filters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zomato.photofilters.FilterPack
import com.zomato.photofilters.imageprocessors.Filter

class MainActivity : AppCompatActivity(), ThumbnailCallback {
    private var activity: Activity? = null
    private var thumbListView: RecyclerView? = null
    private var placeHolderImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity = this
        initUIWidgets()
    }

    private fun initUIWidgets() {
        thumbListView = findViewById(R.id.thumbnails)
        placeHolderImageView = findViewById(R.id.place_holder_imageview)
        placeHolderImageView!!.setImageBitmap(
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                    resources, R.drawable.photo
                ), 640, 640, false
            )
        )
        initHorizontalList()
    }

    private fun initHorizontalList() {
        thumbListView?.setHasFixedSize(true)
        bindDataToAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindDataToAdapter() {
        val context: Context = application
        val handler = Handler()
        handler.post {
            val thumbImage = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.photo
                ), 640, 640, false
            )

            ThumbnailsManager.clearThumbs()

            FilterPack.getFilterPack(this).forEach { filter ->
                ThumbnailsManager.addThumb(
                    ThumbnailItem(thumbImage, filter)
                )
            }

            val thumbs = ThumbnailsManager.processThumbs(context)
            val adapter = ThumbnailsAdapter(thumbs, activity as ThumbnailCallback?)
            thumbListView?.setAdapter(adapter)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onThumbnailClick(filter: Filter?) {
        placeHolderImageView!!.setImageBitmap(
            filter!!.processFilter(
                Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(
                        getResources(), R.drawable.photo
                    ), 640, 640, false
                )
            )
        )
    }

    companion object {
        init {
            System.loadLibrary("NativeImageProcessor")
        }
    }
}
