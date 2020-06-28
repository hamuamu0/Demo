package com.qubin.demo.mvp1;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Printer;

import androidx.annotation.Nullable;

import com.qubin.demo.R;
import com.qubin.demo.mvp1.base.BaseActiviy;

/**
 * author：created by qub
 * date：2020/6/10 16
 * descri：
 */
public class MvpActivity extends BaseActiviy implements MvpView {

    MvpPresent mvpPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mvpPresent = new MvpPresent();
        mvpPresent.attachView(this);

    }

    public void getData(){
        mvpPresent.getData("","");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresent.detachView();
    }

    @Override
    public void showData(Object data) {

    }



    @Override
    public Context getContext() {
        return this;
    }
}
