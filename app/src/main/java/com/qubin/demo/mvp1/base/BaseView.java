package com.qubin.demo.mvp1.base;

import android.content.Context;

/**
 * author：created by qub
 * date：2020/6/10 15
 * descri：
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void showToast(String msg);

    Context getContext();
}
