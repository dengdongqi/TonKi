package com.dengdongqi.tonki.base


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/20.
 * |
 * | explain: Viewpager Adapter
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
</pre> *
 */

class VpAdapter(fm: FragmentManager, private val datas: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return datas.size
    }

    override fun getItem(position: Int): Fragment {
        return datas[position]
    }
}
