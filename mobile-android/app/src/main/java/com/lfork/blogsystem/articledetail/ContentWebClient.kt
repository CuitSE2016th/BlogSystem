package com.lfork.blogsystem.articledetail

import android.webkit.WebView
import android.webkit.WebViewClient

class ContentWebClient: WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        imgReset(view);//重置webview中img标签的图片大小
        // html加载完成之后，添加监听图片的点击js函数
//        addImageClickListner();
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url)

    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private fun  imgReset(view: WebView?) {
        view?.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }
}