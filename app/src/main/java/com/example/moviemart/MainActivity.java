package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moviemart.db.LoginDao;
import com.example.moviemart.db.LoginDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private Button buttonLogin;

    private LoginDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        buttonLogin = findViewById(R.id.buttonLogin);

        LoginDatabase database = LoginDatabase.getInstance(this);
        userDao = database.userDao();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                Login user = new Login();
                user.setName(name);
                userDao.insert(user);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
