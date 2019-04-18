package com.dengdongqi.tonki.utils;

import android.content.Context;
import android.util.Log;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import java.io.File;

/**
 * <pre>
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * ◢══════════════════════════════════════════════════════════════════════
 * |
 * | Created by Tonki on 2019/3/14.
 * |
 * | explain: 解决 kotlin 不能使用鲁班  （implementation 'top.zibin:Luban:1.1.8'）
 * |
 * ◢══════════════════════════════════════════════════════════════════════
 * ◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤
 * <pre>
 */
public class KotlinUseLuban {
    private KotlinUseLuban instance;

    private KotlinUseLuban() {
    }

    public KotlinUseLuban getInstance() {
        if (instance == null) {
            synchronized (KotlinUseLuban.class) {
                if (instance == null) {
                    instance = new KotlinUseLuban();
                }
            }
        }
        return instance;
    }

    /**
     * Luban 压缩图片a
     *
     * @param context
     * @param file     需要压缩的图片文件
     * @param listener 回调接口
     */
    public void imgLuban(Context context, File file, final LuBanListener listener) {
        Log.e("DDQ", "imgLuban start");
        Luban.with(context)
                .load(file)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        listener.onStart();
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.e("DDQ", "LuBanListener onSuccess");
                        listener.onSuccess(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }
                }).launch();
    }

    /**
     * <pre>
     *          Luban
     *          压
     *          缩
     *          图
     *          片
     *          接
     *          口
     *          回
     *          调
     * </pre>
     */
    public interface LuBanListener {
        void onSuccess(File file);

        void onError(Throwable e);

        void onStart();
    }

}
