package com.hooyee.androidxstudy.livedata;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hooyee.androidxstudy.R;
import com.hooyee.androidxstudy.databinding.ActivityLiveDataBinding;

public class LiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityLiveDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data);
        final LiveDataViewModel model = new LiveDataViewModel(new PostalCodeRepository());
        binding.setModel(model);
        model.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("LiveDataActivity onChanged s=" + s);
                model.setTitle(s);
            }
        });
        //createMap(model);
        //observerForeverTest(model);
        switchMapTest(model);
    }

    private void switchMapTest(final LiveDataViewModel model) {
        //此方法地址改变会通知到对邮编的观察，因为这个观察者是地址改变转换来的
        model.postalCode.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("switchMapTest s=" + s);
            }
        });


        //这种调用，地址改变后并不会通知到对邮编的观察
       /* model.getPostalCode("vv").observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("switchMapTest s="+s);
            }
        });*/

    }

    private void observerForeverTest(final LiveDataViewModel model) {
        //不具备生命周期性
        model.getCurrentName().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("LiveDataActivity onChanged s=" + s);
                model.setTitle(s);
            }
        });
    }

    /**
     * 测试mapping方法
     **/
    private void createMap(final LiveDataViewModel model) {
        LiveData<String> map = Transformations.map(model.getCurrentName(), new Function<String, String>() {
            @Override
            public String apply(String input) {
                return "hello  " + input;
            }
        });

        map.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                System.out.println("Transformations onChanged s=" + s);
                model.setTitle(s);
            }
        });
    }
}
