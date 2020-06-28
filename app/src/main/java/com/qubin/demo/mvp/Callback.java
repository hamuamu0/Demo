package com.qubin.demo.mvp;

/**
 * author：created by qub
 * date：2020/6/10 13
 * descri：
 */
public interface Callback {

    void onSuccess(Object object);

    void onFailure(Object object);
}
