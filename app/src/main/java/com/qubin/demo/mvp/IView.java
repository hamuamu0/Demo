package com.qubin.demo.mvp;

/**
 * author：created by qub
 * date：2020/6/10 13
 * descri：
 */
public interface IView {

    void showLoading();

    void dissLoading();

    void getResult(Object object);

    void getFail(String msg);
}
