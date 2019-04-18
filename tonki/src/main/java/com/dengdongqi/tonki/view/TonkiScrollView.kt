package com.dengdongqi.tonki.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.annotation.RequiresApi

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: ScrollView 内 View滑动冲突
 *|
 *| use: ScrollView内WebView滑动事件被抢 ==> webView.setViewNeedSlide(webView)
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
class TonkiScrollView : ScrollView {
    private var scrollViewListener: ScrollViewListener? = null
    //需要滑动事件的子View集合
    private var childs: MutableList<View> = mutableListOf()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {}

    fun setViewNeedSlide(view: View) {
        childs.add(view)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (isNeedSlideViewTouched(ev)) {
            false
        } else {
            super.onInterceptTouchEvent(ev)
        }
    }

    private fun isNeedSlideViewTouched(ev: MotionEvent): Boolean {
        if (childCount == 1) {
            val touchX = ev.x
            val touchY = ev.y + scrollY

            val baseLinear = getChildAt(0) as LinearLayout
            for (i in 0 until baseLinear.childCount) {
                val child = baseLinear.getChildAt(i)
                if (childs.size > 0) {
                    for (j in 0 until childs.size) {
                        if (child::class.java.name == childs[j]::class.java.name) {
                            if (touchX < child.right && touchX > child.left &&
                                touchY < child.bottom && touchY > child.top
                            ) {
                                return true
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    override fun onScrollChanged(x: Int, y: Int, oldx: Int, oldy: Int) {
        super.onScrollChanged(x, y, oldx, oldy)
        if (scrollViewListener != null) {
            scrollViewListener!!.onScrollChanged(this, x, y, oldx, oldy)
        }
    }

    interface ScrollViewListener {
        fun onScrollChanged(scrollView: TonkiScrollView, x: Int, y: Int, oldx: Int, oldy: Int)
    }

    fun setScrollViewListener(scrollViewListener: ScrollViewListener) {
        this.scrollViewListener = scrollViewListener
    }
}
