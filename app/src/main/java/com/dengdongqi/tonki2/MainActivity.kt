package com.dengdongqi.tonki2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dengdongqi.tonki.view.ProgressDialog

class MainActivity : AppCompatActivity() {

    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inits()
    }

/*

//    ◢◤◢◤◢◤◢◤◢◤
//    ══════════════════
//    | ---NotificationHelper 测试---
//    ══════════════════
//    ◢◤◢◤◢◤◢◤◢◤

    private fun inits() {
        val intent = Intent(this,NextActivity::class.java)
        if (NotificationsHelper.getInstance().isNotificationsEnabled) {
            NotificationsHelper.getInstance()
                .setPendingIntentParams(1,NextActivity::class.java)
                .sendNotification(1, "我是小号标题", "我是大号内容", R.mipmap.ic_launcher)

        }else{
            DialogHelper.getInstance().showHintDialog("开启应用通知可及时接受相关消息提示，是否前往开启？",object :CustomDialog.DialogListener{
                override fun doClickButton(btn: Button, dialog: CustomDialog) {

                    NotificationsHelper.getInstance().gotoNotificationSetting(this@MainActivity)
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TonkiConstant.REQUEST_SETTING_NOTIFICATION){
            //resultCode == 0 取消状态
            if (NotificationsHelper.getInstance().isNotificationsEnabled) {
                Log.e(TonkiConstant.TAG,"通知功能正常开启")
            }
        }
    }
    */


/*
//    ◢◤◢◤◢◤◢◤◢◤
//    ══════════════════
//    | ---TonkiDialog 测试---
//    ══════════════════
//    ◢◤◢◤◢◤◢◤◢◤
    private lateinit var dialog: Dialog
    private lateinit var view: View
    fun inits() {
        view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null)
        dialog = TonkiDialog.getInstance()
            .builder(this)
            .setContentView(view)
            .setGravity(Gravity.BOTTOM)
            .setCancelable(false)
            .setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.dialog_bg))
            .setWindowAnimations(R.style.TonkiDialogAnimation)
            .setContentPadding(0, 20, 0, 20)
            .setLayoutParamsHeight(WindowManager.LayoutParams.WRAP_CONTENT)
            .setLayoutParamsWidth(WindowManager.LayoutParams.WRAP_CONTENT)
            .setLayoutParamsX(200)
            .setLayoutParamsY(200)
            .buildDialog()

        val bt = view.findViewById<Button>(R.id.bt)
        bt.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }*/


    /*
//    ◢◤◢◤◢◤◢◤◢◤
//    ══════════════════
//    | ---TonkiScrollView , ProgressWebView , ProgressView 测试---
//    ══════════════════
//    ◢◤◢◤◢◤◢◤◢◤
    fun inits(){
        scrollView.setViewNeedSlide(webView)
        webView.loadUrl("http://www.baidu.com")
    }
*/


    /*
    //    ◢◤◢◤◢◤◢◤◢◤
    //    ══════════════════
    //    |  StatusBarManager 测试---
    //    ══════════════════
    //    ◢◤◢◤◢◤◢◤◢◤
        fun inits(){

            StatusBarManager.getInstance().setStatusBarImmersion(this)

            bt.setOnClickListener {
                if (flag){
                    flag = false
                    StatusBarManager.getInstance().clear21Immersion(this)
                }else{
                    flag = true
                    StatusBarManager.getInstance().setStatusBarImmersion(this)
                }

            }
        }
        */
    fun inits() {
        val p = ProgressDialog(this)
        p.show()
    }

}
