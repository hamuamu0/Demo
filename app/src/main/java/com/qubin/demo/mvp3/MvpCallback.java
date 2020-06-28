package com.qubin.demo.mvp3;

/**
 * author：created by qub
 * date：2020/6/15 17
 * descri：
 */
public interface MvpCallback<T> {

    void onSuccess(T data);

    void onFailure(String msg);

    void onError(String errorMsg);

    void onComplete();
}
