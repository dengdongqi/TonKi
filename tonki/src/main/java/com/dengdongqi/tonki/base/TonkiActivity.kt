package com.dengdongqi.tonki.base

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.ToastUtils
import com.dengdongqi.tonki.R

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: BaseActivity 模板
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
open class TonkiActivity : AppCompatActivity() {

    lateinit var mContext: Context
    lateinit var ivBack: ImageView
    lateinit var ivShare: ImageView
    lateinit var tvTitle: TextView
    lateinit var tvSave: TextView
    var oldSystemUiVisibility: Int = 0
    private lateinit var decorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        //初始化过渡动画
        //必须在 super.onCreate(savedInstanceState)和setContentView(layout)之前
        initTransition()
        super.onCreate(savedInstanceState)
        mContext = this
        //虚拟按键覆盖内容问题
//        KeyboardUtils.fixAndroidBug5497(this)  //        必须setContentView()之后
        //强制竖屏
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        //沉浸式状态栏
//        StatusBarManager.getInstance().setStatusBarImmersion(this)
        //设置自定义状态栏高度为原始状态栏高度
//        initBarHeight(findViewById(R.id.statusBar))
        //状态栏背景高亮色
//        StatusBarCompat.setStatusBarColor(this, Color.WHITE)
    }

    private fun initTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /**
             * 必须在 super.onCreate(savedInstanceState)和setContentView(layout)之前
             */
            //android:theme="@style/MyTheme" 中 添加 <item name="android:windowContentTransitions">true</item>
            window.allowEnterTransitionOverlap = true
            window.allowReturnTransitionOverlap = true
            //资源文件指定过渡动画
            val transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_target)
            window.returnTransition = transition
            window.enterTransition = transition
        }
    }

    /**
     * 设置自定义状态栏高度为原始高度
     *
     * @param customBar
     */
    fun initBarHeight(customBar: View?) {
        if (customBar != null) {
            customBar.visibility = View.VISIBLE
            val linearParams = customBar.layoutParams as LinearLayout.LayoutParams
            linearParams.height = getStatusBarHeight()
            customBar.layoutParams = linearParams
        }
    }

    /**
     * 返回状态栏的高度
     *
     * @return 状态栏的高度
     */
    fun getStatusBarHeight(): Int {
        val resources = Resources.getSystem()
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    //第一次返回计时
    private var firstTime: Long = 0
    /**
     *    双击返回键退出
     * */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val secondTime: Long = System.currentTimeMillis()
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                if (secondTime - firstTime > 2000) {
                    ToastUtils.showShort("再按一次返回键退出")
                    firstTime = secondTime
                } else {
                    //outlogin()
                    //应用退到后台
                    moveTaskToBack(true)
                    ToastUtils.cancel()
                    ActivityUtils.finishAllActivities()
                    //杀死所有的后台服务进程
                    ProcessUtils.killAllBackgroundProcesses()
                }
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }


    /**
     * api >= 5.0 启动 Transition Activity
     */
    fun startActivityForTransition(intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        } else {
            startActivity(intent)
        }
    }

    /**
     * api >= 5.0 启动 Transition Activity
     */
    fun startActivityForResultAndTransition(intent: Intent, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivityForResult(intent, requestCode, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        } else {
            startActivityForResult(intent, requestCode)
        }
    }

    /**
     * api >= 5.0 Transition之后关闭页面
     */
    fun finishPage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        } else {
            finish()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}