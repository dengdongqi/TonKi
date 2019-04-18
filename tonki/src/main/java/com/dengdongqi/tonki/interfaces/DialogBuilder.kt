package com.dengdongqi.tonki.interfaces

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.StyleRes

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: Dialog构造者接口
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
interface DialogBuilder {
    fun builder(context: Context?): DialogBuilder
    fun setContentView(view: View): DialogBuilder
    fun setCancelable(flag: Boolean): DialogBuilder
    fun setGravity(gravity: Int): DialogBuilder
    fun setBackgroundDrawable(drawable: Drawable?): DialogBuilder
    fun setWindowAnimations(@StyleRes resId: Int): DialogBuilder
    fun setContentPadding(left: Int, top: Int, right: Int, bottom: Int): DialogBuilder
    fun setLayoutParamsHeight(height: Int): DialogBuilder
    fun setLayoutParamsWidth(width: Int): DialogBuilder
    fun setLayoutParamsX(x: Int): DialogBuilder
    fun setLayoutParamsY(y: Int): DialogBuilder
    fun buildDialog(): Dialog
}