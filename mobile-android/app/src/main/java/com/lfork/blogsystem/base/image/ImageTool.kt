package com.lfork.blogsystem.base.image

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.Log
import com.lfork.blogsystem.BlogApplication.Companion.context
import com.lfork.blogsystem.R
import com.yalantis.ucrop.UCrop
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import java.io.File

/**
 * Created by 98620 on 2018/11/30.
 */
object ImageTool {

    private val TAG = "ImageTool"

    /**
     * 图片高斯化处理
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun rsBlur(context: Context, source: Bitmap?, radius: Int): Bitmap? {

        if (source == null) {
            return null
        }
        //(network_security_config)
        val renderScript = RenderScript.create(context)

        Log.i(TAG, "scale size:" + source.width + "*" + source.height)

        // Allocate memory for Renderscript to work with
        //(2)
        val input = Allocation.createFromBitmap(renderScript, source)
        val output = Allocation.createTyped(renderScript, input.type)
        //(3)
        // Load up an instance of the specific script that we want to use.
        val scriptIntrinsicBlur =
            ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        //(4)
        scriptIntrinsicBlur.setInput(input)
        //(5)
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius.toFloat())
        //(6)
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output)
        //(7)
        // Copy the output to the blurred bitmap
        output.copyTo(source)
        //(8)
        renderScript.destroy()
        return source
    }


    fun selectPicture(activity: Activity, REQUEST_CODE_CHOOSE: Int) {
        Matisse.from(activity)
            .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
            .countable(true)
            .maxSelectable(1)
            .autoHideToolbarOnSingleTap(true)
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
            .thumbnailScale(0.85f)
            .imageEngine(GlideEngine())
            .capture(true)
            .captureStrategy(CaptureStrategy(true, "com.lfork.blogsystem.fileprovider"))
            .theme(R.style.custom_matisse)
            .forResult(REQUEST_CODE_CHOOSE)
    }


    //剪切图片
    fun cutPicture(picUri: Uri, activity: Activity) {
        val cacheImageUri =
            Uri.fromFile(
                File(
                    activity.externalCacheDir,
                    "crop_portrait.jpg"
                )
            ) //在某些场景还是得用fromFile

        val options = UCrop.Options()
        options.setToolbarColor(ContextCompat.getColor(context!!, R.color.colorBase))
        options.setStatusBarColor(ContextCompat.getColor(context!!, R.color.colorBase))
        options.setToolbarWidgetColor(activity.resources.getColor(R.color.black))
        options.setActiveWidgetColor(activity.resources.getColor(R.color.colorAccent))

        UCrop.of(picUri, cacheImageUri)     //源uri 为content类型的, 目标uri为file类型
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1080, 1920)
            .withOptions(options)
            .start(activity)
    }
}
