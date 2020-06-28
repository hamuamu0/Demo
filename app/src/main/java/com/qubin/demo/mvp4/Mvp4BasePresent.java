package com.qubin.demo.mvp4;

/**
 * author：created by qub
 * date：2020/6/19 14
 * descri：
 */
public class Mvp4BasePresent<V extends Mvp4IView> {

    private V mvp4View;

    public void attachView(V mvp4View){
        this.mvp4View = mvp4View;
    }

    public void detachView(){
        mvp4View = null;
    }

    public boolean isAttachView(){
        return mvp4View != null;
    }

    public V getMvp4View(){
        return mvp4View;
    }
}
