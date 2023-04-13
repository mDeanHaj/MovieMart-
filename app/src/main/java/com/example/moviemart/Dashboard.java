package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView mName;
    Button mLogoutButton, mAdminButton, mSearchMovieButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mName = findViewById(R.id.name);
        mLogoutButton = findViewById(R.id.logoutButton);
        mSearchMovieButton = findViewById(R.id.searchMovieButton);
        mAdminButton = findViewById(R.id.adminButton);
        String user = getIntent().getStringExtra("name");
        mName.setText(user);

        if (user.equals("admin2")) {
            mAdminButton.setVisibility(View.VISIBLE);
        } else {
            mAdminButton.setVisibility(View.INVISIBLE);
        }

        mSearchMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, SearchMovie.class));
            }
        });

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AdminActivity.class));
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Dashboard.this, LoginActivity.class));
            }
        });
    }
}