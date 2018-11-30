package com.lfork.blogsystem.imagebrowser

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lfork.blogsystem.R
import android.content.Intent
import android.view.MenuItem
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.github.chrisbanes.photoview.PhotoView
import com.lfork.blogsystem.utils.setupToolBar
import kotlinx.android.synthetic.main.image_browser_act.*
import java.util.*


class ImageBrowserActivity : AppCompatActivity() {

    private var url: String? = null
    private var position: Int = 0

    private var photoView: PhotoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_browser_act)
//        position = intent.getIntExtra("position", 0)
//        val bundle = intent.getBundleExtra("bundle")
//        url = bundle.getStringArrayList("url")
        url = intent.getStringExtra("url")
        photoView = findViewById(R.id.big_image)
        ImageBinding.setImageNoCache(photoView, url!![position])
        setupToolBar(toolbar, "Image Browser")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> finish()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun openImageBrowser(context: Context, urls: ArrayList<String>, position: Int) {
        val intent = Intent(context, ImageBrowserActivity::class.java)
        val bundle = Bundle()
        bundle.putStringArrayList("url", urls)
        intent.putExtra("bundle", bundle)
        intent.putExtra("position", position)
        context.startActivity(intent)
    }


    fun openImageBrowser(context: Context, url: String) {
        val intent = Intent(context, ImageBrowserActivity::class.java)
        val bundle = Bundle()
        intent.putExtra("url", url)
        context.startActivity(intent)
    }
}
