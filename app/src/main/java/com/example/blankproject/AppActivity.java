package com.example.blankproject;

import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppActivity extends ComponentActivity {
    private void fillRecycleView(List<String> items){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewItemAdapter(items));
    }
    private void toastAlert(){
        Toast.makeText(this,"Not yet implemented",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        RadioButton preferencesRadioButton = findViewById(R.id.preferencesRadioButton);
        RadioButton fileRadioButton = findViewById(R.id.fileRadioButton);
        RadioButton databaseRadioButton = findViewById(R.id.databaseRadioButton);
        EditText shopEditText = findViewById(R.id.shopEditText);
        EditText clerkEditText= findViewById(R.id.clerkEditText);

        Button loadButton = findViewById(R.id.loadButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button resetButton = findViewById(R.id.resetButton);

        RecyclerView rcv = findViewById(R.id.recyclerView);

        Drawable originalDrawable = shopEditText.getBackground();
        preferencesRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shopEditText.setText("");
                clerkEditText.setText("");
                if(b){
                    shopEditText.setBackground(originalDrawable);
                    shopEditText.setEnabled(true);
                    clerkEditText.setEnabled(false);

                }
            }
        });
        fileRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shopEditText.setText("");
                clerkEditText.setText("");

                if(b){
                    shopEditText.setBackground(originalDrawable);
                    shopEditText.setEnabled(false);
                    clerkEditText.setEnabled(true);
                }
            }
        });
        databaseRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shopEditText.setText("");
                clerkEditText.setText("");
                if (b){
                    shopEditText.setBackground(originalDrawable);
                    shopEditText.setEnabled(true);
                    clerkEditText.setEnabled(true);

                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(preferencesRadioButton.isChecked() == false){ toastAlert(); return; }
                SharedPreferences prefs = getSharedPreferences("PrefContent", Context.MODE_PRIVATE);
                Integer counter = prefs.getInt("counter",0);
                counter = (counter<1)? 1:counter;
                prefs.edit().remove(String.format("Shop-%s", counter)).apply();
                counter--;
                prefs.edit().putInt("counter",counter).apply();
                shopEditText.setText("");
                clerkEditText.setText("");
                shopEditText.setBackground(originalDrawable);
            }
        });



        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(preferencesRadioButton.isChecked() == false){ toastAlert(); return;}
                String text = shopEditText.getEditableText().toString();
                shopEditText.setText("");
                clerkEditText.setText("");
                if(text.trim().isEmpty()){
                    shopEditText.setBackgroundResource(R.drawable.red_border);
                }
                else{
                    SharedPreferences prefs = getSharedPreferences("PrefContent", Context.MODE_PRIVATE);
                    Integer counter = prefs.getInt("counter",0);
                    counter++;
                    prefs.edit().putString(String.format("Shop-%s", counter), text).apply();
                    prefs.edit().putInt("counter",counter).apply();
                    shopEditText.setBackground(originalDrawable);
                }
            }
        });


        loadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(preferencesRadioButton.isChecked() == false){ toastAlert(); return;}
                SharedPreferences prefs = getSharedPreferences("PrefContent", Context.MODE_PRIVATE);
                List<String> x = new ArrayList<>();

                Map<String, ?> allEntries = prefs.getAll();
                allEntries.remove("counter");
                if(allEntries.size()==0){
                    x.add("No prefs saved");
                }
                else{
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    x.add(entry.getValue().toString());
                }
                }
                fillRecycleView(x);
                shopEditText.setText("");
                clerkEditText.setText("");
                shopEditText.setBackground(originalDrawable);

            }
        });
    }
}