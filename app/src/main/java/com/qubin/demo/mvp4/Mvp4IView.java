package com.qubin.demo.mvp4;

import android.content.Context;

/**
 * author：created by qub
 * date：2020/6/19 14
 * descri：
 */
public interface Mvp4IView {

    void showLoadingDialog();

    void hideLoadingDialog();

    void showToast();

    Context getContext();



}
