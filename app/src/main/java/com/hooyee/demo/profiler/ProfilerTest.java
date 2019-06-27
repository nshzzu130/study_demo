package com.hooyee.demo.profiler;
/**
 * @desc
 * 1.Call Chart选项卡提供一个方法跟踪的图形表示，其中一个方法调用(或调用者)的周期和时间在水平轴上表示，
 * 而它的callees则显示在垂直轴上。对系统api的方法调用以橙色显示，调用您的应用程序自己的方法以绿色显示，
 * 方法调用第三方api(包括java语言api)以蓝色显示
 * 2.Bottom Up选项卡对于那些消耗最多(或最少)CPU时间的方法的排序方法很有用,self 表示用于执行其自己的代码而不是其callees的方法的总时间
 * children 表示用于执行callees而不是自己的代码的总时间
 * @author hooyee
 * @date   2019/6/14
**/
public class ProfilerTest {


    public static void run() {

        ProfilerTest profilerTest = new ProfilerTest();

        profilerTest.methodD();
        profilerTest.methodA();
        profilerTest.methodB();
        profilerTest.methodC();

    }

    private void methodD() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void methodA() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        methodC();
    }

    public void methodB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodC() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
