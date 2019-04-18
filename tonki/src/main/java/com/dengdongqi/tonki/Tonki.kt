package com.dengdongqi.tonki

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import java.lang.reflect.InvocationTargetException

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: Tonki 初始化
 *|
 *| use: 在Application类onCreate()中初始化 --> Tonki.init(application)
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
class Tonki private constructor() {

    init {
    }

    companion object {

        var application: Application? = null
        fun init(content: Context) {
            init(content.applicationContext as Application?)
        }

        fun init(app: Application?) {
            if (application == null) {
                application = app ?: getApplicationByReflect()
            } else {
                if (app != null && app !== application) {
                    application = app
                }
            }
            Utils.init(application)
        }

        fun checkInit() {
            if (application == null) {
                throw UnsupportedOperationException("请先在Application类中初始化 Tonki.init(application)")
            }
        }

        private fun getApplicationByReflect(): Application {
            try {
                @SuppressLint("PrivateApi")
                val activityThread = Class.forName("android.app.ActivityThread")
                val thread = activityThread.getMethod("currentActivityThread").invoke(null)
                val app = activityThread.getMethod("getApplication").invoke(thread)
                    ?: throw NullPointerException("u should init first")
                return app as Application
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
            throw NullPointerException("请先在Application类中初始化 Tonki.init(application)")
        }
    }
}