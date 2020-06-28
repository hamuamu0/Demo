package com.qubin.demo.mvp1;

/**
 * author：created by qub
 * date：2020/6/10 15
 * descri：
 */
public interface MvpCallback<T> {

    void onSuccess(T data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
