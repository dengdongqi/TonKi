package com.dengdongqi.tonki.view

import android.app.Dialog
import android.content.Context
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.dengdongqi.tonki.R
import java.io.Serializable


/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| explain: 通用对话框
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
class CustomDialog(context: Context) : Dialog(context, R.style.CustomProgressDialog) {

    companion object {
        internal val LEFT = 0
        internal val RIGHT = 1
        internal val BOTH = 2
        internal val NONE = 3
    }

    private val context: TextView
    private val okBtn: Button
    private val cancle: Button
    private val hLine: View
    private val vLine: View
    private val btnLayout: LinearLayout

    init {
        setContentView(R.layout.dialog_layout)
        val window = window
        if (window != null) {
            window.attributes.gravity = Gravity.CENTER
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        this.setCancelable(true)
        this.setCanceledOnTouchOutside(false)
        this.context = this.findViewById<View>(R.id.context) as TextView
        cancle = this.findViewById<View>(R.id.cancle) as Button
        okBtn = this.findViewById<View>(R.id.ok) as Button
        vLine = this.findViewById(R.id.vline)
        hLine = this.findViewById(R.id.hline)
        btnLayout = this.findViewById<View>(R.id.dialog_btn_layout) as LinearLayout
        setLeftBtnListener("取消", null)
    }

    /**
     * 设置对话框样式，现实一个按钮，还是现实两个按钮
     * @param model default BOTH
     */
    fun setModel(model: Int) {
        when (model) {
            CustomDialog.LEFT -> {
                okBtn.visibility = View.GONE
                cancle.visibility = View.VISIBLE
                vLine.visibility = View.GONE
                hLine.visibility = View.VISIBLE
                btnLayout.visibility = View.VISIBLE
            }
            CustomDialog.RIGHT -> {
                cancle.visibility = View.GONE
                okBtn.visibility = View.VISIBLE
                vLine.visibility = View.GONE
                hLine.visibility = View.VISIBLE
                btnLayout.visibility = View.VISIBLE
            }
            CustomDialog.BOTH -> {
                cancle.visibility = View.VISIBLE
                okBtn.visibility = View.VISIBLE
                vLine.visibility = View.VISIBLE
                hLine.visibility = View.VISIBLE
                btnLayout.visibility = View.VISIBLE
            }
            CustomDialog.NONE -> {
                cancle.visibility = View.GONE
                okBtn.visibility = View.GONE
                vLine.visibility = View.GONE
                hLine.visibility = View.GONE
                btnLayout.visibility = View.GONE
            }
        }
    }

    /**
     * 设置消息提示
     */
    fun setMessage(strMessage: String) {
        context.text = strMessage
    }

    /**
     * 设置左右按钮多标签以及监听起
     * @param text
     * @param listener
     */
    fun setLeftBtnListener(text: String, listener: DialogListener?) {
        cancle.text = text
        cancle.setOnClickListener {
            listener?.doClickButton(okBtn, this@CustomDialog)
            this@CustomDialog.dismiss()
        }
    }

    fun setRightBtnListener(text: String, listener: DialogListener?) {
        okBtn.text = text
        okBtn.setOnClickListener {
            listener?.doClickButton(okBtn, this@CustomDialog)
            this@CustomDialog.dismiss()
        }
    }

    /**
     * 设置左右按钮颜色
     * @param color
     */
    fun setLeftBtnColor(color: Int) {
        cancle.setTextColor(color)
    }

    fun setRightBtnColor(color: Int) {
        okBtn.setTextColor(color)
    }

    /**
     * 延迟2s消失
     */
    fun holdDismiss(sec: Int) {
        this.show()
        HoldTimer(sec).start()
    }

    private inner class HoldTimer(second: Int) : CountDownTimer(second * 1000L, 1000L), Serializable {
        override fun onFinish() {
            this.cancel()
            this@CustomDialog.dismiss()
        }

        override fun onTick(millisUntilFinished: Long) {}

    }

    interface DialogListener {
        fun doClickButton(btn: Button, dialog: CustomDialog)
    }


}
