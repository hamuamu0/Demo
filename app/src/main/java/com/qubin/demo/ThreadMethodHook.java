package com.qubin.demo;

import android.util.Log;

import com.taobao.android.dexposed.XC_MethodHook;

/**
 * author：created by qub
 * date：2020/5/21 17
 * descri：
 */
public class ThreadMethodHook extends XC_MethodHook {
    private final String TAG = "ThreadMethodHook";
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        Log.i(TAG, "thread:" + t + ", started..");
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        Log.i(TAG, "thread:" + t + ", exit..");
    }


}
