package com.qubin.demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * author：created by qub
 * date：2020/6/4 18
 * descri：
 */
public interface GitHubService {
    @GET("/wxarticle/chapters/json")
    Call<ChapterBean> getChapterMsg();

    @FormUrlEncoded
    @POST("user/login")
    Call<Object> getLogin(@Field("username") String username, @Field("password") String password);
}
