package com.hooyee.demo;

import androidx.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * @BindingAdapter可以自定义setter方法
**/
public class BindingAdapterTest {

    @BindingAdapter({"android:text"})
    public static void setText(TextView textView, String text) {
        textView.setText("text=" + text);
    }

}
