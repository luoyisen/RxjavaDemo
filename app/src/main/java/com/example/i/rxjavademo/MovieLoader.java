package com.example.i.rxjavademo;


import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by I on 2017/8/11.
 */

public class MovieLoader extends ObjectLoader {

    private MovieService mMovieService;

    public MovieLoader() {
        mMovieService = RetrofitServiceManager.getInstance().create(MovieService.class);//创建api实例，之后才能调用API中的方法,比如getTop250
        Log.e("11111", "2");
    }

    /**
     * 获取电影列表
     */
    public Observable<List<Movie>> getMovie(int start, int count) {//观察者是整个movie列表
        Log.e("11111", "1");
        return Zhidingxiancheng(mMovieService.getTop250(start, count))//该方法只是将传进来的observable指定了线程

                .map(new Func1<MovieSubject, List<Movie>>() {

                    @Override
                    public List<Movie> call(MovieSubject movieSubject) {
                        //Call其实在Retrofit中就是行使网络请求并处理返回值的类，调用的时候会把需要拼接的参数传递进去，
                        return movieSubject.subjects;
                    }
                });
    }
}
