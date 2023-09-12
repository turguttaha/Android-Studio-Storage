package com.example.blankproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView myTextView = findViewById(R.id.textView);
        myTextView.setText("Hello Android UCLL");
    }
}