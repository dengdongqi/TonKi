package com.dengdongqi.tonki.utils

import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * <pre>
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 *◢══════════════════════════════════════════════════════════════════════
 *|
 *| Created by Tonki on 2019/3/14.
 *|
 *| explain: View 2 PDF
 *|
 *| viewPager.setPageTransformer(true, new ScalePageTransformer());
 *|
 *◢══════════════════════════════════════════════════════════════════════
 *◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
class ScalePageTransformer : ViewPager.PageTransformer {

    companion object {
        private val MIN_SCALE = 0.75f
    }

    override fun transformPage(page: View, position: Float) {
        when {
            position < -1.0f -> {
                page.scaleX = MIN_SCALE
                page.scaleY = MIN_SCALE
            }
            position <= 0.0f -> {
                page.alpha = 1.0f
                page.translationX = 0.0f
                page.scaleX = 1.0f
                page.scaleY = 1.0f
            }
            position <= 1.0f -> {
                page.alpha = 1.0f - position
                page.translationX = -page.width * position
                val scale = MIN_SCALE + (1.0f - MIN_SCALE) * (1.0f - position)
                page.scaleX = scale
                page.scaleY = scale
            }
            else -> {
                page.scaleX = MIN_SCALE
                page.scaleY = MIN_SCALE
            }
        }
    }

}
