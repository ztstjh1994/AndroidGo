package com.example.tjh.myapplication.domain;


import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.*;

/**
 * Created by TJH on 2017/12/5.
 */

public interface Api {
    @GET("toutiao/index")
    Observable<NewsBean> getNews(@Query("key")String key, @Query("type")String type);
}
