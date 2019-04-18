package com.dengdongqi.tonki.helper

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.StyleRes
import com.blankj.utilcode.util.ActivityUtils
import com.dengdongqi.tonki.Tonki
import com.dengdongqi.tonki.interfaces.DialogBuilder

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/14.
 * |
 * | explain: 支持全自定义Dialog
 * |
 * | eg:
 * |       private lateinit var dialog: Dialog
 * |       private lateinit var view: View
 * |        fun inits() {
 * |            view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null)
 * |
 * |            dialog = TonkiDialog.getInstance().builder(this)
 * |                    .setContentView(view)
 * |                    .setCancelable(false)
 * |                    .setGravity(Gravity.BOTTOM)
 * |                    .setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.dialog_bg))
 * |                    .setWindowAnimations(R.style.TonkiDialogAnimation)
 * |                    .setContentPadding(0, 20, 0, 20)
 * |                    .setLayoutParamsHeight(WindowManager.LayoutParams.WRAP_CONTENT)
 * |                    .setLayoutParamsWidth(WindowManager.LayoutParams.WRAP_CONTENT)
 * |                    .setLayoutParamsX(200)
 * |                    .setLayoutParamsY(200)
 * |                    .buildDialog()
 * |
 * |            val bt = view.findViewById<Button>(R.id.bt)
 * |            bt.setOnClickListener {
 * |                dialog.dismiss()
 * |            }
 * |            dialog.show()
 * |        }
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * </pre>
 */
object TonkiDialog : DialogBuilder {
    private var dialog: Dialog? = null
    private var window: Window? = null
    private var lp: WindowManager.LayoutParams? = null

    /**
     * builder
     * @param context 上下文
     * @return this@DialogBuilder
     * */
    override fun builder(context: Context?): DialogBuilder {
        dialog = if (context == null) {
            Dialog(ActivityUtils.getTopActivity())
        } else {
            Dialog(context)
        }
        return this
    }

    /**
     * 设置Dialog自定义布局
     * @param view Dialog自定义布局
     * @return this@DialogBuilder
     * */
    override fun setContentView(view: View): DialogBuilder {
        if (dialog != null) {
            dialog!!.setContentView(view)
            window = dialog!!.window
            lp = window!!.attributes
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * dialog弹出后会点击屏幕或物理返回键dialog是否消失
     * @param flag
     * @return this@DialogBuilder
     * */
    override fun setCancelable(flag: Boolean): DialogBuilder {
        if (dialog != null) {
            dialog!!.setCancelable(flag)
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 设置dialog Gravity
     * @param gravity  eg : Gravity.BOTTOM 底部显现dialog
     * @return this@DialogBuilder
     * */
    override fun setGravity(gravity: Int): DialogBuilder {
        if (window != null) {
            window!!.setGravity(gravity)
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 将此dialog的背景更改为自定义Drawable
     * @param drawable
     * @return this@DialogBuilder
     * */
    override fun setBackgroundDrawable(drawable: Drawable?): DialogBuilder {
        if (window != null) {
            window!!.setBackgroundDrawable(drawable)
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 设置此 dialog 进出动画
     * @param resId
     * @return this@DialogBuilder
     * */
    override fun setWindowAnimations(@StyleRes resId: Int): DialogBuilder {
        if (window != null) {
            window!!.setWindowAnimations(resId)
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 设置此dialog的内容的Padding
     * unit : px
     * @param left 左 padding
     * @param top
     * @param right
     * @param bottom
     * @return this@DialogBuilder
     * */
    override fun setContentPadding(left: Int, top: Int, right: Int, bottom: Int): DialogBuilder {
        if (window != null) {
            window!!.decorView.setPadding(left, top, right, bottom)
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 设置此窗口的内容的height
     * unit : px
     * @param height
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsHeight(height: Int): DialogBuilder {
        if (lp != null) {
            lp!!.height = height
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 设置此窗口的内容的width
     * unit : px
     * @param width
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsWidth(width: Int): DialogBuilder {
        if (lp != null) {
            lp!!.width = width
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 设置此窗口 x轴正位移
     * unit : px
     * @param x
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsX(x: Int): DialogBuilder {
        if (lp != null) {
            lp!!.x = x
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * 设置此窗口 y轴正位移
     * unit : px
     * @param y
     * @return this@DialogBuilder
     * */
    override fun setLayoutParamsY(y: Int): DialogBuilder {
        if (lp != null) {
            lp!!.y = y
        } else {
            throw ExceptionInInitializerError("请按顺序调用 builder() -> setContentView() -> ... ->buildDialog()")
        }
        return this
    }

    /**
     * buildDialog
     * @return dialog
     * */
    override fun buildDialog(): Dialog {
        window!!.attributes = lp
        return dialog!!
    }

}