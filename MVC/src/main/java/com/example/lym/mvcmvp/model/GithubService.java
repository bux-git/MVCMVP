package com.example.lym.mvcmvp.model;

import com.example.lym.mvcmvp.constant.Constant;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface GithubService {

    /**
     * 获取指定帐号github详情
     * @param userName
     * @return
     */
   @GET("users/{userName}/repos")
    Observable<List<Repository>> publicRepositories(@Path("userName") String userName);

    /**
     * 获取Java详情
     * @param javaUrl
     * @return
     */
    @GET
    Observable<List<Repo>> javaRepositories(@Url String javaUrl);

    class Factory{

        public static GithubService create(){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(GithubService.class);
        }
    }
}
