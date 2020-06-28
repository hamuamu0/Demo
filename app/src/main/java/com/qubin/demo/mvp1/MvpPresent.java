package com.qubin.demo.mvp1;

import com.qubin.demo.mvp1.base.BasePresenter;

/**
 * author：created by qub
 * date：2020/6/10 16
 * descri：
 */
public class MvpPresent extends BasePresenter<MvpView> {

    public void getData(String username,String password){
        if (!isAttachView()){
            return;
        }

        getMvpView().showLoading();

        MvpModel.getNetData(username, password, new MvpCallback<Object>() {
            @Override
            public void onSuccess(Object data) {
                if (isAttachView()){
                    getMvpView().showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isAttachView()){
                    getMvpView().showToast(msg);
                }
            }

            @Override
            public void onError() {
                if (isAttachView()){
                    getMvpView().showToast("error");
                }
            }

            @Override
            public void onComplete() {
                if (isAttachView()){
                    getMvpView().hideLoading();
                }
            }
        });
    }
}
