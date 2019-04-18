package com.dengdongqi.tonki.view

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.dengdongqi.tonki.R
import com.dengdongqi.tonki.interfaces.SpeedListener

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/20.
 * |
 * | explain: 旋转View
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
</pre> *
 */


class SpinView : ImageView, SpeedListener {
    private var mRotateDegrees: Float = 0.toFloat()
    private var mFrameTime: Int = 0
    private var mNeedToUpdateView: Boolean = false
    private var mUpdateViewRunnable: Runnable? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {}

    init {
        init()
    }

    private fun init() {
        this.setImageResource(R.drawable.kprogresshud_spinner)
        this.mFrameTime = 83
        this.mUpdateViewRunnable = object : Runnable {
            override fun run() {
                this@SpinView.mRotateDegrees = this@SpinView.mRotateDegrees + 30.0f
                this@SpinView.mRotateDegrees =
                    if (this@SpinView.mRotateDegrees < 360.0f) this@SpinView.mRotateDegrees else this@SpinView.mRotateDegrees - 360.0f
                this@SpinView.invalidate()
                if (this@SpinView.mNeedToUpdateView) {
                    this@SpinView.postDelayed(this, this@SpinView.mFrameTime.toLong())
                }
            }
        }
    }

    override fun setAnimationSpeed(scale: Float) {
        this.mFrameTime = (83.0f / scale).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.rotate(this.mRotateDegrees, (this.width / 2).toFloat(), (this.height / 2).toFloat())
        super.onDraw(canvas)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.mNeedToUpdateView = true
        this.post(this.mUpdateViewRunnable)
    }

    override fun onDetachedFromWindow() {
        this.mNeedToUpdateView = false
        super.onDetachedFromWindow()
    }
}