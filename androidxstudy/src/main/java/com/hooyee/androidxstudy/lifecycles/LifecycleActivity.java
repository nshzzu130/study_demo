package com.hooyee.androidxstudy.lifecycles;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.hooyee.androidxstudy.R;

public class LifecycleActivity extends AppCompatActivity {
    ViewDataBinding viewDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle);

        //getLifecycle().addObserver(new LifecycleContorl());
        new LifecycleContorl(getLifecycle());
    }
}
