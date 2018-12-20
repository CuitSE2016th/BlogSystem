package com.lfork.blogsystem.base.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View

class WrapContentHeightViewPager : ViewPager {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var height = 0
        //下面遍历所有child的高度
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(
                widthMeasureSpec,
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            val h = child.measuredHeight
            //采用最大的view的高度。
            if (h > height) {
                height = h
            }
        }
        setMeasuredDimension(measuredWidth, height)
    }
}