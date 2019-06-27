package com.hooyee.androidxstudy.workmanager;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author hooyee
 * @desc 学习Worker和WorkerManager的使用
 * @date 2019/6/12
 **/
public class WorkerManagerTest {

    public static void run(FragmentActivity activity) {
        System.out.println("AndroidXTest threadId=" + Thread.currentThread().getId());
        //normal();
        //beginWith();
        //constraints();
        //periodWork();
        //combine();
        //tag();
        param(activity);
        rxwork();
        System.out.println("AndroidXTest end threadId=" + Thread.currentThread().getId());
    }

    /**
     * rxjava2
    **/
    private static void rxwork() {


    }

    /**
     * 参数传递依赖activityd的生命周期
     **/
    private static void param(FragmentActivity activity) {
        Data myData = new Data.Builder()
                .putInt("param1", 4)
                .putInt("param2", 5)
                .build();

        OneTimeWorkRequest paramwork =
                new OneTimeWorkRequest.Builder(ParamWorker.class)
                        .setInputData(myData)
                        .addTag("param")
                        .build();
        WorkManager.getInstance().enqueue(paramwork);
        WorkManager.getInstance().getWorkInfoByIdLiveData(paramwork.getId()).observe(activity, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    System.out.println("outputData=" + workInfo.getOutputData().getInt("result", 0));
                    WorkManager.getInstance().cancelAllWorkByTag("param");
                }
            }
        });


    }

    private static void tag() {
        OneTimeWorkRequest workRequest =
                new OneTimeWorkRequest.Builder(WorkerA.class)
                        .addTag("cleanup")
                        .build();

        WorkManager.getInstance().enqueue(workRequest);

        try {
            List<WorkInfo> workInfos = WorkManager.getInstance().getWorkInfosByTag("cleanup").get();
            System.out.println("workinfos=" + workInfos.get(0).getTags().toArray()[0]);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void combine() {
        OneTimeWorkRequest workA = new OneTimeWorkRequest.Builder(WorkerA.class).build();
        OneTimeWorkRequest workB = new OneTimeWorkRequest.Builder(WorkerB.class).build();

        OneTimeWorkRequest workC = new OneTimeWorkRequest.Builder(WorkerC.class).build();
        OneTimeWorkRequest workD = new OneTimeWorkRequest.Builder(WorkerD.class).build();

        OneTimeWorkRequest workE = new OneTimeWorkRequest.Builder(WorkerE.class).build();

        WorkContinuation chain1 = WorkManager.getInstance()
                .beginWith(workA)
                .then(workB);
        WorkContinuation chain2 = WorkManager.getInstance()
                .beginWith(workC)
                .then(workD);
        WorkContinuation chain3 = WorkContinuation
                /*.combine(Arrays.asList(new WorkContinuation[]{chain1}))*/
                .combine(Arrays.asList(new WorkContinuation[]{chain1, chain2}))
                .then(workE);
        chain3.enqueue();
    }

    /**
     * 测试不通过,没有定期执行，并且worker一直保持着，也没有取消
     **/
    private static void periodWork() {
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkerB.class, 1, TimeUnit.SECONDS).build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }

    private static void constraints() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(WorkerA.class)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance().enqueue(workRequest);
    }

    private static void beginWith() {
        OneTimeWorkRequest workRequestA = new OneTimeWorkRequest.Builder(WorkerA.class).build();
        OneTimeWorkRequest workRequestB = new OneTimeWorkRequest.Builder(WorkerB.class).build();
        WorkManager.getInstance()
                .beginWith(workRequestA)
                .then(workRequestB)
                .enqueue();
    }

    private static void normal() {
        System.out.println("AndroidXTest threadId=" + Thread.currentThread().getId());
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(WorkerA.class).build();
        WorkManager.getInstance().enqueue(workRequest);
    }


}
