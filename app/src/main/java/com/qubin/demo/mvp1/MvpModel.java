package com.qubin.demo.mvp1;

import com.qubin.demo.GitHubService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author：created by qub
 * date：2020/6/10 15
 * descri：
 */
public class MvpModel {

    public static void getNetData(String username, String password, final MvpCallback<Object> callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        retrofit.create(GitHubService.class)
                .getLogin(username, password)
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        callback.onSuccess(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        callback.onFailure(t.getMessage().toString());
                    }
                });

        callback.onComplete();

    }
}
