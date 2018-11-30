package com.lfork.blogsystem.base.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.lfork.blogsystem.base.databinding.ImageBinding;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * 给知乎的Matisse图片选择器使用
 * Created by 98620 on 2018/4/14.
 */


public class GlideEngine implements ImageEngine {

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        setImage(imageView, uri);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {

    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        setImage(imageView, uri);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {

    }


    private void setImage(ImageView imageView, Uri uri){
        ImageBinding.setImageWithDiskCache(imageView, uri);
    }


    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
