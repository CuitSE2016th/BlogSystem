//package com.lfork.blogsystem.base.image
//
//import android.app.Activity
//import android.content.Context
//import android.widget.ImageView
//
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners
//import com.bumptech.glide.request.RequestOptions
//import com.lfork.blogsystem.base.databinding.ImageBinding
//import com.youth.banner.loader.ImageLoader
//
//class BannerImageLoader : ImageLoader() {
//    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
//        //Glide 加载图片简单用法
//        if (isValidContextForGlide(context)) {
////            ImageBinding.setImageNoCache(imageView, path)
//            Glide.with(context).load(path)
//                .apply(RequestOptions.bitmapTransform( RoundedCorners(20)))
//                .into(imageView)
//        }
//    }
//
//    private fun isValidContextForGlide(context: Context?): Boolean {
//        if (context == null) {
//            return false
//        }
//        if (context is Activity) {
//            val activity = context as Activity?
//            return !activity!!.isDestroyed && !activity.isFinishing
//        }
//        return true
//    }
//}