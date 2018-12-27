package com.lfork.blogsystem.base.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lfork.blogsystem.R;

/**
 * Created by 98620 on 2018/4/7.
 * <p>
 * 对不同的图片加载方式的封装
 */

public class ImageBinding {


    @BindingAdapter(value = {"imageUrl", "placeDrawableId"})
    public static void setImage(ImageView view, String imageUrl, int placeDrawableId) {
        if(view.getContext() == null){
            return;
        }
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).placeholder(placeDrawableId);
        Glide.with(view.getContext()).load(imageUrl).apply(options).into(view);
    }

    @BindingAdapter(value = {"imageUrlNoCache", "placeDrawableId"})
    public static void setImageNoCache(ImageView view, String imageUrl, int placeDrawableId) {
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(placeDrawableId);
        Glide.with(view.getContext()).load(imageUrl).apply(options).into(view);
    }

    @BindingAdapter({"setImageNoCache"})
    public static void setImageNoCache(ImageView view, String imageUrl) {
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true);
        Glide.with(view.getContext()).load(imageUrl).apply(options).into(view);
    }

    public static void setImageNoCache(ImageView view, Object path) {
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true);
        Glide.with(view.getContext()).load(path).apply(options).into(view);
    }

    @BindingAdapter({"setImageWithDiskCache"})
    public static void setImageWithDiskCache(ImageView view, String imageUrl) {
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL).skipMemoryCache(false);
        Glide.with(view.getContext()).load(imageUrl).apply(options).into(view);
    }

    @BindingAdapter({"setImage"})
    public static void setImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public static void setImageNoCache(ImageView view, Uri uri) {
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true);
        Glide.with(view.getContext()).load(uri).apply(options).into(view);
    }

    public static void setImage(ImageView view, Uri uri) {
        Glide.with(view.getContext()).load(uri).into(view);
    }


    public static void setImageWithDiskCache(ImageView view, Uri uri) {
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL).skipMemoryCache(false);
        Glide.with(view.getContext()).load(uri).apply(options).into(view);
    }

    @BindingAdapter({"setImageToCardViewBackgroundWithDiskCache"})
    public static void setImageToBackgroundWithDiskCache(final CardView view, String imageUrl) {
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL).skipMemoryCache(false);
        Glide.with(view.getContext()).load(imageUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, Transition<? super Drawable> transition) {
                view.setBackground(resource);
            }
        });
    }


}
