package com.lfork.blogsystem.articledetail;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class JavaScriptInterface {

    private Context context;

    public JavaScriptInterface(Context context) {
        this.context = context;
    }

    //点击图片回调方法
    //必须添加注解,否则无法响应
    @JavascriptInterface
    public void openImage(String img) {
        Log.i("TAG", "响应点击事件!");
        Intent intent = new Intent();
        intent.putExtra("image", img);
//        intent.setClass(context, BigImageActivity.class);//BigImageActivity查看大图的类，自己定义就好
//        context.startActivity(intent);


    }
}