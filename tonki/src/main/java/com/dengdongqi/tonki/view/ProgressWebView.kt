package com.dengdongqi.tonki.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.ConvertUtils

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/14.
 * |
 * | explain: 带进度条webView
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
</pre></pre> */
class ProgressWebView : WebView {
    private var progressView: ProgressView? = null//进度条

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {}

    init {
        inits()
    }

    private fun inits() {
        //初始化进度条
        progressView = ProgressView(context!!)
        progressView!!.layoutParams =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(4f))

        progressView!!.setProgress(10)
        //把进度条加到Webview中
        addView(progressView)
        webChromeClient = MyWebChromeClient()
        webViewClient = MyWebViewClient()
    }

    private inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress == 100) {
                //加载完毕进度条消失
                progressView!!.visibility = View.GONE
            } else {
                //更新进度
                progressView!!.setProgress(newProgress)
            }
            super.onProgressChanged(view, newProgress)
        }
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(wv: WebView, url: String?): Boolean {
            if (url == null) return false
            try {
                if (url.startsWith("weixin://") //微信

                    || url.startsWith("alipays://") //支付宝

                    || url.startsWith("mailto://") //邮件

                    || url.startsWith("tel://")//电话

                    || url.startsWith("dianping://")//大众点评

                    || url.startsWith("baiduboxlite://")
                    || url.startsWith("baidumap://")
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context!!.startActivity(intent)
                    return true
                }//其他自定义的scheme
            } catch (e: Exception) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                return true//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
            }

            //处理http和https开头的url
            wv.loadUrl(url)
            return true
        }
    }

    /**
     * 设置进度条颜色
     *
     * @param color 色值
     */
    fun setProgressColor(color: Int) {
        progressView!!.setColor(color)
    }

    /**
     * 设置进度条宽度
     *
     * @param width
     */
    fun setProgressStrokeWidth(width: Float) {
        progressView!!.setStrokeWidth(width)
    }
}

