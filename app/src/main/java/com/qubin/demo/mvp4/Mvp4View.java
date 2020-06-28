package com.qubin.demo.mvp4;

/**
 * author：created by qub
 * date：2020/6/19 15
 * descri：
 */
public interface Mvp4View<T> extends Mvp4IView {

    void showData(T data);
}
