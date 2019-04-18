package com.dengdongqi.tonki.utils

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: MIN_CLICK_DELAY_TIME之内是否双击，用于防止用户快速点击按钮开启2-N个页面
 *|
 *| use: if(DoubleUtils.isFastDoubleClick){ onclick(...) }
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
object DoubleUtils {
    private var MIN_CLICK_DELAY_TIME = 400
    private var lastClickTime: Long = 0

    fun setTime(long: Int){
        MIN_CLICK_DELAY_TIME = long
    }

    /**
     * 两次点击时间之间的点击间隔不能少于 MIN_CLICK_DELAY_TIME 毫秒
     * @return true -->  大于 MIN_CLICK_DELAY_TIME 毫秒
     */
    fun isFastDoubleClick(): Boolean {
        var flag = false
        val curClickTime = System.currentTimeMillis()
        if (curClickTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
            flag = true
        }
        lastClickTime = curClickTime
        return flag
    }
}