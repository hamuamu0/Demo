package com.qubin.demo.mvp4;

/**
 * author：created by qub
 * date：2020/6/19 14
 * descri：
 */
public interface Mvp4Callback<T> {

    void onSuccess(T data);

    void onFailure(String failureMsg);

    void onError(String errorMsg);

    void onComplete();


}
