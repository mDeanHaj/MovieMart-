package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView mName;
    Button mLogoutButton, mAdminButton, mSearchMovieButton, mOrderHistoryButton, mCancelOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mName = findViewById(R.id.name);
        mLogoutButton = findViewById(R.id.logoutButton);
        mSearchMovieButton = findViewById(R.id.searchMovieButton);
        mOrderHistoryButton = findViewById(R.id.orderHistoryButton);
        mCancelOrderButton = findViewById(R.id.cancelOrderButton);
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

        mOrderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, OrderHistory.class));
            }
        });

        mCancelOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, CancelOrder.class));
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
                SharedPreferences preferences = getSharedPreferences("MovieMart", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Dashboard.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}