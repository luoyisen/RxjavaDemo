package com.example.i.rxjavademo;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by I on 2017/8/11.
 */

public class ObjectLoader {
    /**
     * 线程调度器（Scheduler）是将RxJava从同步观察者模式转到异步观察者模式的一个重要工具。
     * subscribeOn(): 指定 subscribe() 所发生的线程，
     * 即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
     * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
     */
    protected  <T> Observable<T> Zhidingxiancheng(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())//事件生成的线程
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());//事件消费的线程

    }


}
