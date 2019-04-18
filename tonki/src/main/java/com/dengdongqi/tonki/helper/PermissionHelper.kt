package com.dengdongqi.tonki.helper


import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.dengdongqi.tonki.Tonki

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: Permission 帮助类
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
object PermissionHelper {

    fun requestCamera(requestAgainReason: String, listener: OnPermissionCallbackListener) {
        request(requestAgainReason, listener, PermissionConstants.CAMERA, PermissionConstants.STORAGE)
    }

    fun requestStorage(requestAgainReason: String, listener: OnPermissionCallbackListener) {
        request(requestAgainReason, listener, PermissionConstants.STORAGE)
    }

    fun requestPhone(requestAgainReason: String, listener: OnPermissionCallbackListener) {
        request(requestAgainReason, listener, PermissionConstants.PHONE)
    }

    fun requestSms(requestAgainReason: String, listener: OnPermissionCallbackListener) {
        request(requestAgainReason, listener, PermissionConstants.SMS)
    }

    fun requestLocation(requestAgainReason: String, listener: OnPermissionCallbackListener) {
        request(requestAgainReason, listener, PermissionConstants.LOCATION)
    }

    private fun request(
        callbackListener: OnPermissionCallbackListener,
        @PermissionConstants.Permission vararg permissions: String
    ) {
        request("该权限为必须权限，未允许将影响程序正常使用", callbackListener, *permissions)
    }

    /**
     * 请求权限
     *
     * @param requestAgainReason 再次请求权限时的提示语
     * @param callbackListener   成功失败回调
     * @param permissions        权限...
     */
    private fun request(
        requestAgainReason: String,
        callbackListener: OnPermissionCallbackListener?,
        @PermissionConstants.Permission vararg permissions: String
    ) {
        PermissionUtils.permission(*permissions)
            //设置拒绝权限后再次请求的回调接口
            .rationale { shouldRequest
                ->
                DialogHelper.showRationaleDialog(shouldRequest, requestAgainReason)

            }
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                    callbackListener?.onPermissionGranted()
                    LogUtils.d(permissionsGranted)
                }

                override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {
                    callbackListener?.onPermissionDenied(permissionsDeniedForever, permissionsDenied)
                    LogUtils.e(
                        "拒绝(用户勾选不再提示)：$permissionsDeniedForever\n拒绝:$permissionsDenied"
                    )
                }
            })
            .request()
    }

    interface OnPermissionCallbackListener {
        fun onPermissionGranted()
        fun onPermissionDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>)
    }

}
