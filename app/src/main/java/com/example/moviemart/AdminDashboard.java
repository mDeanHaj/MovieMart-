package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {

    TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mName = findViewById(R.id.name);
        String user = getIntent().getStringExtra("name");
        mName.setText(user);

    }
}