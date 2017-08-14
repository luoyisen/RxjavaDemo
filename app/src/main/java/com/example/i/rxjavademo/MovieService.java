package com.example.i.rxjavademo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by I on 2017/8/11.
 */

public interface MovieService {
    //获取豆瓣电影Top250榜单
    @GET("top250")
    Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count") int count);
}
