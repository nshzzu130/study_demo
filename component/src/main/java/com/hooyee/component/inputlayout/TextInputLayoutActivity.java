package com.hooyee.component.inputlayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.hooyee.component.R;

public class TextInputLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        TextInputLayout textInputLayout = findViewById(R.id.input_name);
        //textInputLayout.setError("i am a error msg");


    }
}
