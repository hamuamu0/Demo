package com.qubin.demo.mvp;

/**
 * author：created by qub
 * date：2020/6/10 13
 * descri：
 */
public class IPresenter {

    private IView iView;

    private IModel iModel;

    public IPresenter(IView iView){
        this.iView = iView;
        iModel = new IModel();
    }

    public void getNetResult(String username,String password){

        iModel.getNetWorkResult(username, password, new Callback() {
            @Override
            public void onSuccess(Object object) {
                iView.getResult(object);
            }

            @Override
            public void onFailure(Object object) {
                iView.getFail(object.toString());
            }
        });

    }



}
