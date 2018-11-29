package com.lfork.blogsystem.base.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

internal class MarqueeTextView : android.support.v7.widget.AppCompatTextView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun isFocused(): Boolean {
        return true
    }
}