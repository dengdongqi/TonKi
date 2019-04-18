package com.dengdongqi.tonki.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.Gravity
import com.dengdongqi.tonki.R

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/20.
 * |
 * | explain: 可取消的IOS风格等待dialog
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * </pre>
 */
class ProgressDialog(context: Context) : Dialog(context, R.style.progress_dialog) {

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        val window = this.window
        if (window != null) {
            window.attributes.gravity = Gravity.CENTER
            window.attributes.dimAmount = 0.1f
            window.setWindowAnimations(R.style.DialogWindowStyle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.progress_dialog_layout)
    }

    override fun show() {
        var activity: Activity? = null
        activity = getActivity(context)
        if (activity != null) {
            if (!activity.isFinishing) {
                super.show()
            }
        }
    }

    private fun getActivity(context: Context): Activity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        throw IllegalStateException("ProgressDialog的Context不是一个Activity.")
    }

    override fun dismiss() {
        if (!isShowing) {
            return
        }
        super.dismiss()
    }
}