package com.hooyee.androidxstudy.workmanager;

import android.content.Context;
import androidx.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ParamWorker extends Worker {
    public ParamWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Data inputData = getInputData();
        System.out.println("do Work ParamWorker threadId=" + Thread.currentThread().getId());
        int param1 = inputData.getInt("param1", 0);
        System.out.println("do Work ParamWorker param1=" + param1);
        int param2 = inputData.getInt("param2", 0);
        System.out.println("do Work ParamWorker param2=" + param2);
        Data output=new Data.Builder()
                .putInt("result",param1+param2).build();
        return Result.success(output);
    }
}
