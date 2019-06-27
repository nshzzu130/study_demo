package com.hooyee.rxjavastudy;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MergeConcatZip {

    /**
     * test zip 参数可以不一致，有个组合函数，组合函数最后一个参数类型为返回类型，前面2个参数对应前面2个obserable返回类型，但返回个数为最小的集合个数
     **/
    public void zip() {
        Observable.zip(getObservable1(), getObservable2(), new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return s + "(" + integer + ")";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("zip s=" + s);
            }
        });
    }

    private Observable<String> getObservable3() {
        String[] value = {"male", "female"};
        return Observable.from(value).subscribeOn(Schedulers.io());
    }

    private Observable<String> getObservable2() {
        String[] value = {"zhangsan", "lisiz", "wangwu"};
        return Observable.from(value).subscribeOn(Schedulers.io());
    }

    private Observable<Integer> getObservable1() {
        return Observable.from(new Integer[]{3, 5, 7, 9, 11}).subscribeOn(Schedulers.io());
    }

    /**
     * test merge，参数类型必须一致，随机无序执行
     **/
    public void merge() {
        Observable.merge(getObservable3(), getObservable2()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("merge s=" + s);
            }
        });
    }

    /**
     * test concat 参数类型必须一致，顺序执行
     **/
    public void concat() {
        Observable.concat(getObservable3(), getObservable2()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("concat s=" + s);
            }
        });
    }
}
