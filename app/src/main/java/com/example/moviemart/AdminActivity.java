package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button mReturnButton, mExistMovie, mViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mReturnButton = findViewById(R.id.returnButton);
//        mAddMovie = findViewById(R.id.addMovie);
        mExistMovie = findViewById(R.id.existMovie);
//        mRemoveMovie = findViewById(R.id.removeMovie);
        mViewUsers = findViewById(R.id.viewUsersButton);

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, Dashboard.class));
            }
        });

        // Add a Movie Button

        mExistMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ExistingMovie.class));
            }
        });

        // Remove a Movie Button

        mViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, AdminUsers.class));
            }
        });
    }
}