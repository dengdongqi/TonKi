package com.dengdongqi.tonki.helper

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.TimeUtils
import com.dengdongqi.tonki.Tonki
import com.dengdongqi.tonki.constant.TonkiConstant
import java.lang.reflect.InvocationTargetException
import java.util.*

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: Notification 帮助类
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
object NotificationsHelper {  //Notify
    private var manager: NotificationManager? = null
    private val id = "Notification_ID"
    private val name = "消息通知"
    private var pendingIntent: PendingIntent? = null
    private val CHECK_OP_NO_THROW = "checkOpNoThrow"
    private val OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION"
    private var notificationManager: NotificationManager? = null

    val isNotificationsEnabled: Boolean
        get() {
            notificationManager =
                Tonki.application!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= 24) {
                return notificationManager!!.areNotificationsEnabled()
            } else if (Build.VERSION.SDK_INT >= 19) {
                val appOps = Tonki.application!!.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
                val appInfo = Tonki.application!!.applicationInfo
                val pkg = Tonki.application!!.applicationContext.packageName
                val uid = appInfo.uid
                try {
                    val appOpsClass = Class.forName(AppOpsManager::class.java.name)
                    val checkOpNoThrowMethod =
                        appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String::class.java)
                    val opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION)
                    val value = opPostNotificationValue.get(Int::class.java) as Int
                    return checkOpNoThrowMethod.invoke(appOps, value, uid, pkg) as Int == AppOpsManager.MODE_ALLOWED
                } catch (e: ClassNotFoundException) {
                    return true
                } catch (e: NoSuchMethodException) {
                    return true
                } catch (e: NoSuchFieldException) {
                    return true
                } catch (e: InvocationTargetException) {
                    return true
                } catch (e: IllegalAccessException) {
                    return true
                } catch (e: RuntimeException) {
                    return true
                }

            } else {
                return true
            }
        }

    fun gotoNotificationSetting(activity: Activity) {
        val appInfo = activity.applicationInfo
        val pkg = activity.applicationContext.packageName
        val uid = appInfo.uid
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val intent = Intent()
                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, pkg)
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, uid)
                //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
                intent.putExtra("app_package", pkg)
                intent.putExtra("app_uid", uid)
                activity.startActivityForResult(intent, TonkiConstant.REQUEST_SETTING_NOTIFICATION)
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("package:" + Tonki.application!!.packageName)
                activity.startActivityForResult(intent, TonkiConstant.REQUEST_SETTING_NOTIFICATION)
            } else {
                val intent = Intent(Settings.ACTION_SETTINGS)
                activity.startActivityForResult(intent, TonkiConstant.REQUEST_SETTING_NOTIFICATION)
            }
        } catch (e: Exception) {
            val intent = Intent(Settings.ACTION_SETTINGS)
            activity.startActivityForResult(intent, TonkiConstant.REQUEST_SETTING_NOTIFICATION)
//            LogUtils.e(e)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createNotificationChannel() {
        val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
        getManager().createNotificationChannel(channel)
    }

    private fun getManager(): NotificationManager {
        if (manager == null) {
            manager = Tonki.application!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return manager!!
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getChannelNotification(title: String, content: String, @DrawableRes icon: Int): Notification.Builder {
        return Notification.Builder(Tonki.application!!, id)
//            .setTicker("您有新的通知信息，请注意查看")
            .setContentTitle(content)
            .setContentText(TimeUtils.date2String(Date()))
            .setSubText(title)
            .setSmallIcon(icon)
            .setLargeIcon(BitmapFactory.decodeResource(Tonki.application!!.resources, icon))
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
    }

    @SuppressLint("WrongConstant")
    fun getNotification25(title: String, content: String, @DrawableRes icon: Int): NotificationCompat.Builder {
        return NotificationCompat.Builder(Tonki.application!!)
//            .setTicker("您有新的通知信息，请注意查看")
            .setContentTitle(content)
            .setContentText(TimeUtils.date2String(Date()))
            .setSubText(title)
            .setSmallIcon(icon)
            .setLargeIcon(BitmapFactory.decodeResource(Tonki.application!!.resources, icon))
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
    }

    /**
     * 发送通知
     * */
    fun sendNotification(id: Int, title: String, content: String, @DrawableRes icon: Int){
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel()
            val notification = getChannelNotification(title, content, icon).build()
            getManager().notify(id, notification)
        } else {
            val notification = getNotification25(title, content, icon).build()
            getManager().notify(id, notification)
        }
    }

    /**
     * 通知点击事件
     * @param tagId 通知点击对应目标请求码，不同通知尽量保持不一致
     * @param cls 目标对象   eg: activity
     * */
    fun setPendingIntentParams(tagId: Int, cls: Class<*>):NotificationsHelper{
        pendingIntent = PendingIntent.getActivity(
            Tonki.application,
            tagId,
            Intent(Tonki.application, cls),
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        return this
    }

}
