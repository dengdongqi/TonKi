package com.dengdongqi.tonki.base

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/20.
 * |
 * | explain: BaseFragment 模板
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
</pre> *
 */
class TonkiFragment : Fragment() {
    var activity: Activity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = if (getActivity() == null) ActivityUtils.getTopActivity() else getActivity()
    }

    /**
     * api >= 5.0 启动 Transition Activity
     */
    fun startActivityForTransition(intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            activity!!.startActivity(intent)
        }
    }

    /**
     * api >= 5.0 启动 Transition Activity
     */
    fun startActivityForResultAndTransition(intent: Intent, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.startActivityForResult(
                intent,
                requestCode,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            )
        } else {
            activity!!.startActivityForResult(intent, requestCode)
        }
    }

    /**
     * api >= 5.0 Transition之后关闭页面
     */
    fun finishPage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.finishAfterTransition()
        } else {
            activity!!.finish()
        }
    }
}
