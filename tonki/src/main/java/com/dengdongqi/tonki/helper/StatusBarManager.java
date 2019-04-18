package com.dengdongqi.tonki.helper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.githang.statusbar.StatusBarCompat;

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/18.
 * |
 * | explain: 状态栏管理类
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
public class StatusBarManager {
    private static StatusBarManager instance;
    //api >= 21
    private static int oldSystemUiVisibility;
    private View decorView;
    //19 > api > 21
    private WindowManager.LayoutParams localLayoutParams;

    private StatusBarManager() {
    }

    public static StatusBarManager getInstance() {
        if (instance == null) {
            synchronized (StatusBarManager.class) {
                if (instance == null) {
                    instance = new StatusBarManager();
                }
            }
        }
        return instance;
    }

    /**
     * 设置activity为沉浸式状态栏
     *
     * @param activity
     */
    public void setStatusBarImmersion(Activity activity) {
        decorView = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 21) {
            oldSystemUiVisibility = decorView.getSystemUiVisibility();
//            LogUtils.e(oldSystemUiVisibility + "");
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //状态栏背景色透明
            StatusBarCompat.setStatusBarColor(activity, Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            //低于4.4的android系统版本不存在沉浸式状态栏
            //设置状态栏不可见
            BarUtils.setStatusBarVisibility(activity, false);
        }
    }

    /**
     * 取消activity的沉浸式状态栏
     *
     * @param activity
     */
    public void clear21Immersion(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (decorView != null) {
                decorView.setSystemUiVisibility(oldSystemUiVisibility);
                //状态栏背景色白色(Android 6.0以下没有官方的API可以把状态栏的图标及字体设置为深色)
                if (Build.VERSION.SDK_INT >= 23) {
                    StatusBarCompat.setStatusBarColor(activity, Color.WHITE);
                } else {
                    //StatusBarCompat.setStatusBarColor(activity, Color.parseColor("#757575"));
                    BarUtils.setStatusBarVisibility(activity, false);
                }
            }
        }
    }

}
