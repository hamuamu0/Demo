package com.qubin.demo.mvp4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author：created by qub
 * date：2020/6/19 14
 * descri：
 */
public abstract class BaseActivity extends AppCompatActivity implements Mvp4IView {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("正在加载...");
        initData();

    }

    abstract public int initLayout();

    abstract public void initData();

    @Override
    public void showLoadingDialog() {
        progressDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showToast() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
