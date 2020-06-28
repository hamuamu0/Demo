package com.qubin.demo.mvp2;

import com.qubin.demo.GitHubService;
import com.qubin.demo.mvp1.MvpCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author：created by qub
 * date：2020/6/11 16
 * descri：
 */
public class UserDataModel extends BaseModel<String> {
    @Override
    public void execute(final MvpCallback<String> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        retrofit.create(GitHubService.class)
                .getLogin(mParams[0], mParams[1])
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
