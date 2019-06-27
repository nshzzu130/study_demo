package com.hooyee.androidxstudy.workmanager;

import android.content.Context;
import androidx.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerD extends Worker {

    public WorkerD(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        System.out.println("do Work D threadId=" + Thread.currentThread().getId());
        return Result.success();
    }
}
