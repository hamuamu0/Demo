package com.qubin.demo.mvp;

import com.qubin.demo.GitHubService;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author：created by qub
 * date：2020/6/10 13
 * descri：
 */
public class IModel {

    public  void getNetWorkResult(String username, String password, final Callback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        retrofit.create(GitHubService.class)
                .getLogin(username,password)
                .enqueue(new retrofit2.Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        callback.onSuccess(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        callback.onFailure("失败...");
                    }
                });
    }
}
