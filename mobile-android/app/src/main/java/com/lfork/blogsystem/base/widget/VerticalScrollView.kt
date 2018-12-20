package com.lfork.blogsystem.base.widget

import android.annotation.TargetApi
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

/**
 * @ explain:这个ScrlloView不拦截水平滑动事件，
 * 是用来解决 ScrollView里面嵌套ViewPager使用的
 * @ author：xujun on 2016/10/25 15:28
 * @ email：gdutxiaoxu@163.com
 */
class VerticalScrollView : ScrollView {

    private var mDownPosX = 0f
    private var mDownPosY = 0f

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x
        val y = ev.y

        val action = ev.action
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mDownPosX = x
                mDownPosY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = Math.abs(x - mDownPosX)
                val deltaY = Math.abs(y - mDownPosY)
                // 这里是否拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截

                if (deltaX > deltaY) {// 左右滑动不拦截
                    return false
                }
            }
        }

        return super.onInterceptTouchEvent(ev)
    }
}