package com.dengdongqi.tonki2

import android.app.Application
import com.dengdongqi.tonki.Tonki

/**
 * Created by Tonki on 2019/3/13.
 * Copyright © 2019, 长沙豆子信息技术有限公司, All rights reserved.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Tonki.init(this.applicationContext)
    }
}