package com.hooyee.rxjavastudy;


import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author hooyee
 * @desc rxjava实现timer 倒计时功能
 * @date 2019/6/27
 **/
public class TimerTask implements LifecycleObserver {
    Subscriber<Integer> subscriber = null;
    TimerCallBack timerCallBack;

    public TimerTask(FragmentActivity activity, TimerCallBack callBack) {
        activity.getLifecycle().addObserver(this);
        this.timerCallBack = callBack;
    }

    public void start(final int time) {
        subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("integer=" + integer);
                if (timerCallBack != null) {
                    timerCallBack.callBack(integer);
                }
            }
        };
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long aLong) {
                        return time - aLong.intValue();
                    }
                })
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("doOnNext");
                    }
                })
                .take(time + 1)
                .subscribe(subscriber);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        if (subscriber != null) {
            subscriber.unsubscribe();
            subscriber = null;
        }
    }

    public interface TimerCallBack {
        void callBack(int count);
    }

}
