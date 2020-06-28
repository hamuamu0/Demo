package com.qubin.demo.mvp2;

import com.qubin.demo.mvp1.MvpCallback;

/**
 * author：created by qub
 * date：2020/6/11 16
 * descri：
 */
public class Presenter {

    public static void getNetData(){
        DataModel.request(Token.API_USER_DATA)
                .params("")
                .execute(new MvpCallback() {
                    @Override
                    public void onSuccess(Object data) {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
