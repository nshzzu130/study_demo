package com.hooyee.component;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.hooyee.component.inputlayout.TextInputLayoutActivity;

public class Component {

    public static void run(AppCompatActivity activity) {
        System.out.println("===================Component=====================");
        //activity.startActivity(new Intent(activity, TextInputLayoutActivity.class));
    }
}
