package com.qubin.demo;

import android.util.Log;

/**
 * author：created by qub
 * date：2020/4/26 14
 * descri：
 */
public class LauchTimer {

    private static long currentTime;

    //记录开始时间
    public static void recordStartTime(){
        currentTime = System.currentTimeMillis();
    }

    //记录启动完成时间
    public static void recordEndTime(){
        long stopTime = System.currentTimeMillis() - currentTime;
        Log.i("LauchTime","RecordTime:" + stopTime + "ms");
    }
}
