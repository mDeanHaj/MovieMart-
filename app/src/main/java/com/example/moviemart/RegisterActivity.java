package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviemart.db.MovieDatabase;
import com.example.moviemart.db.UserDao;

public class RegisterActivity extends AppCompatActivity {

    EditText userId, password;
    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userId.getText().toString();
                String passwordText = password.getText().toString();

                if (username.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill Username and Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                MovieDatabase movieDatabase = MovieDatabase.getInstance(getApplicationContext());
                UserDao userDao = movieDatabase.userDao();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int count = userDao.countUsersWithUsername(username);

                        if (count > 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            User user = new User();
                            user.setUserId(username);
                            user.setPassword(passwordText);

                            userDao.registerUser(user);
                            LoggedInUser.getInstance().setUser(user);
                            LoggedInUser.getInstance().saveUserIdToPreferences(getApplicationContext());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Username Registered!", Toast.LENGTH_SHORT).show();
                                    userId.setText("");
                                    password.setText("");
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private Boolean validateInput(User user){
        if(user.getUserId().isEmpty() || user.getPassword().isEmpty()){
            return false;
        }
        return true;
    }
}