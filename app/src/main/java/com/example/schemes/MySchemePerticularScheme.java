package com.example.schemes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MySchemePerticularScheme extends AppCompatActivity {

    TextView perticularSchemeName,perticularSchemeDept,perticularSchemeOverview,perticularSchemebenefit,perticularSchemeDoc;
    Button link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scheme_perticular_scheme);

        final String name,department,overview,benefits,requireddocuments,addressUrl;

        name = getIntent().getStringExtra("name");
        department = getIntent().getStringExtra("department");
        overview = getIntent().getStringExtra("overview");
        benefits = getIntent().getStringExtra("benefits");
        requireddocuments = getIntent().getStringExtra("documentRequired");
        addressUrl = getIntent().getStringExtra("link");

        perticularSchemeName = findViewById(R.id.perticularSchemeName);
        perticularSchemeDept = findViewById(R.id.perticularSchemeDept);
        perticularSchemeOverview = findViewById(R.id.perticularSchemeOverview);
        perticularSchemebenefit = findViewById(R.id.perticularSchemebenefit);
        perticularSchemeDoc = findViewById(R.id.perticularSchemeDoc);

        link = findViewById(R.id.link);

        perticularSchemeName.setText(name);
        perticularSchemeDept.setText(department);
        perticularSchemeOverview.setText(overview);
        perticularSchemebenefit.setText(benefits);
        perticularSchemeDoc.setText(requireddocuments);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(addressUrl));
                startActivity(intent);
            }
        });

    }
}
