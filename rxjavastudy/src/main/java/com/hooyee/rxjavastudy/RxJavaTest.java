package com.hooyee.rxjavastudy;

import androidx.fragment.app.FragmentActivity;

/**
 * @author hooyee
 * @desc rxjava test
 * @date 2019/6/10
 **/
public class RxJavaTest {

    public static void run() {
//        new MergeConcatZip().concat();
//        new MergeConcatZip().merge();
//        new MergeConcatZip().zip();
        System.out.println("====================rxjava=======================");
        //new ThreadSwitch().doNext();
       /* new TimerTask(new FragmentActivity(), new TimerTask.TimerCallBack() {
            @Override
            public void callBack(int count) {
                System.out.println("count="+count);
            }
        }).start(5);*/

       new FlatMapConcatMap().testConcatFlat();

    }
}
