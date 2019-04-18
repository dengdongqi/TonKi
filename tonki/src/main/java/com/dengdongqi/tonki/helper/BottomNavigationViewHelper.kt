package com.dengdongqi.tonki.helper

import android.annotation.SuppressLint
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/25.
 * |
 * | explain: 取消底部导航栏item大于3时的位移动画
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * </pre>
 */
object BottomNavigationViewHelper {
    /**
     * Google design库的BottomNavigationView底部导航栏item大于3个时有位移动画不协调，更改成3个item的缩放样式
     *  打包混淆的时候，不能混淆这个成员变量！！！！
     *    -keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
     *        boolean mShiftingMode;
     *     }
     * @param bottomNavigationView 要更改的底部导航栏
     * */
    @SuppressLint("RestrictedApi")
    fun disableShiftMode(bottomNavigationView: BottomNavigationView) {
        val menuView = bottomNavigationView.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0..menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView?
                item?.setShifting(false)
                item?.setChecked(item.itemData.isChecked)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}