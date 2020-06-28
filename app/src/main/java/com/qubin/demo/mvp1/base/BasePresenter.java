package com.qubin.demo.mvp1.base;

/**
 * author：created by qub
 * date：2020/6/10 15
 * descri：
 */
public class BasePresenter<V extends BaseView> {

    private V mvpView;

    public void attachView(V mvpView){
        this.mvpView = mvpView;
    }

    public void detachView(){
        mvpView = null;
    }

    public boolean isAttachView(){
        return mvpView != null;
    }

    public V getMvpView(){
        return mvpView;
    }
}
