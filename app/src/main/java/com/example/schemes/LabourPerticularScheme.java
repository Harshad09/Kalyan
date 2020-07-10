package com.example.schemes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import Model.LabourModel;

public class LabourPerticularScheme extends AppCompatActivity {

    private TextView perticular,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_perticular_scheme);

        LabourModel labourModel = new LabourModel();
        String name = getIntent().getStringExtra("scheme");
        String information = getIntent().getStringExtra("info");
        perticular = findViewById(R.id.perticularScheme);
        info = findViewById(R.id.info);
        info.setText(information);
        perticular.setText(name);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}
