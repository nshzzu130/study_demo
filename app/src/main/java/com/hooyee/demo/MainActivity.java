package com.hooyee.demo;

import android.content.Intent;
import android.view.View;

import com.hooyee.androidxstudy.AndroidXTest;
import com.hooyee.component.Component;
import com.hooyee.demo.base.BaseActivity;
import com.hooyee.demo.basequickadapter.TestBaseQuickAdapterActivity;
import com.hooyee.demo.databinding.ActivityMainBinding;
import com.hooyee.demo.profiler.ProfilerTest;
import com.hooyee.rxjavastudy.RxJavaTest;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void doDrawView() {
        mvvmDemo();
        RxJavaTest.run();
        AndroidXTest.run(this);
        Component.run(this);
    }

    private void mvvmDemo() {
        MainViewModel viewModel = new MainViewModel("mvvm demo");
        //bindings.setVariable(BR.data,viewModel);
        bindings.setData(viewModel);

        bindings.clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ProfilerTest.run();
                startActivity(new Intent(MainActivity.this, TestBaseQuickAdapterActivity.class));

            }
        });

    }
}
