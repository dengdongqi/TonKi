package com.dengdongqi.tonki.helper


import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils.OnRationaleListener.ShouldRequest
import com.blankj.utilcode.util.ScreenUtils
import com.dengdongqi.tonki.Tonki
import com.dengdongqi.tonki.view.CustomDialog

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: Dialog 帮助类
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
object DialogHelper {

    /**
     * 提示开启权限理由
     */
    fun showRationaleDialog(shouldRequest: ShouldRequest, requestAgainReason: String) {
        val topActivity = ActivityUtils.getTopActivity() ?: return
        val outDialog = CustomDialog(topActivity)
        outDialog.setTitle("温馨提示")
        outDialog.setMessage(requestAgainReason)
        outDialog.setCancelable(true)
        setBackgroundAlpha(topActivity, 0.8f)
        outDialog.setLeftBtnListener("取消", object : CustomDialog.DialogListener {
            override fun doClickButton(btn: Button, dialog: CustomDialog) {
                shouldRequest.again(true)
                setBackgroundAlpha(topActivity, 1f)
            }
        })
        outDialog.setRightBtnColor(Color.parseColor("#ff0000"))
        outDialog.setRightBtnListener("确定", object : CustomDialog.DialogListener {
            override fun doClickButton(btn: Button, dialog: CustomDialog) {
                shouldRequest.again(true)
            }
        })
        outDialog.show()
    }

    /**
     * App提示语
     */
    fun showHintDialog(content: String, listener: CustomDialog.DialogListener) {
        val topActivity = ActivityUtils.getTopActivity() ?: return
        val outDialog = CustomDialog(topActivity)
        outDialog.setTitle("温馨提示")
        outDialog.setMessage(content)
        outDialog.setCancelable(true)
        setBackgroundAlpha(topActivity, 0.8f)
        outDialog.setLeftBtnListener("取消", null)
        outDialog.setRightBtnColor(Color.parseColor("#ff0000"))
        outDialog.setRightBtnListener("确定", listener)
        outDialog.show()

        outDialog.setOnDismissListener {
            setBackgroundAlpha(topActivity, 1f)
        }
    }

    /**
     * 显示Dialog
     *
     * @param Dialog 要显示的Dialog
     * @param p      多少屏幕倍宽
     */
    fun showDialog(Dialog: Dialog, p: Float) {
        Dialog.show()
        val window = Dialog.window
        if (window != null) {
            window.setGravity(Gravity.CENTER)
            val lp = window.attributes
            lp.width = (ScreenUtils.getScreenWidth() * p).toInt() // 宽度设置为屏幕的 p 配
            Dialog.window!!.attributes = lp
        }
    }

    /**
     * 设置页面的透明度
     *
     * @param bgAlpha 1表示不透明
     */
    fun setBackgroundAlpha(activity: Activity, bgAlpha: Float) {
        val lp = activity.window.attributes
        lp.alpha = bgAlpha
        if (bgAlpha == 1f) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        } else {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
        activity.window.attributes = lp
    }

}
