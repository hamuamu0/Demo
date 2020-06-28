package com.qubin.demo.mvp2;

import com.qubin.demo.mvp.Callback;
import com.qubin.demo.mvp1.MvpCallback;

/**
 * author：created by qub
 * date：2020/6/11 16
 * descri：
 */
public abstract class BaseModel<T> {

    protected String[] mParams;

    public BaseModel params(String... arg){
        mParams = arg;
        return this;
    }

    public abstract void execute(MvpCallback<T> callback);


}
