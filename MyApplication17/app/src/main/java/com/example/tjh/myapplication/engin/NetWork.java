package com.example.tjh.myapplication.engin;

import com.example.tjh.myapplication.BuildConfig;
import com.example.tjh.myapplication.domain.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TJH on 2017/12/5.
 */

public class NetWork {
    private static Retrofit retrofit;

    /*返回Retrofit*/
    public static Retrofit getRetrofit(){
        if(retrofit ==null){
            OkHttpClient httpClient;
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if(BuildConfig.DEBUG){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logging);
            }
            httpClient = builder.build();
            //创建Retrofit构建器
            retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }
    public static Api createApi(){
        return NetWork.getRetrofit().create(Api.class);
    }
}
