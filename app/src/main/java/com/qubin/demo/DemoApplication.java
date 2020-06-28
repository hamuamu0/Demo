package com.qubin.demo;

import android.app.Application;
import android.content.Context;
import android.os.Debug;

import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * author：created by qub
 * date：2020/3/21 10
 * descri：
 */
public class DemoApplication extends Application {

    private static RefWatcher refWatcher;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LauchTimer.recordStartTime();



    }

    @Override
    public void onCreate() {
        super.onCreate();
        Debug.startMethodTracing("App");
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        refWatcher = LeakCanary.install(this);

        Debug.stopMethodTracing();
    }

    public static RefWatcher getRefWatcher(){
        return refWatcher;
    }
}
