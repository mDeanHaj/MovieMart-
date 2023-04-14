package com.example.moviemart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviemart.db.MovieDatabase;
import com.example.moviemart.db.UserDao;

public class LoginActivity extends AppCompatActivity {

    EditText userId, password;
    Button regUser, login;
    Button adminButton;

    private static final String TEST_USERNAME = "testuser1";
    private static final String TEST_PASSWORD = "testuser1";
    private static final String ADMIN_USERNAME = "admin2";
    private static final String ADMIN_PASSWORD = "admin2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        regUser = findViewById(R.id.regUser);
        login = findViewById(R.id.login);

        adminButton = findViewById(R.id.adminButton);

        int loggedInUserId = LoggedInUser.getInstance().getUserIdFromPreferences(getApplicationContext());
        if (loggedInUserId != -1) {
            MovieDatabase movieDatabase = MovieDatabase.getInstance(getApplicationContext());
            UserDao userDao = movieDatabase.userDao();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    User user = userDao.getUserById(loggedInUserId);
                    if (user != null) {
                        LoggedInUser.getInstance().setUser(user);
                        String name = user.getUserId();
                        startActivity(new Intent(LoginActivity.this, Dashboard.class).putExtra("name", name));
                    }
                }
            }).start();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all Fields!", Toast.LENGTH_SHORT).show();
                } else {
                    if (userIdText.equals(TEST_USERNAME) && passwordText.equals(TEST_PASSWORD)) {
                        startActivity(new Intent(LoginActivity.this, Dashboard.class)
                                .putExtra("name", TEST_USERNAME));
                    } else if (userIdText.equals(ADMIN_USERNAME) && passwordText.equals(ADMIN_PASSWORD)) {
                        startActivity(new Intent(LoginActivity.this, Dashboard.class)
                                .putExtra("name", ADMIN_USERNAME));
                        adminButton.setVisibility(View.VISIBLE);
                    } else {
                        MovieDatabase movieDatabase = MovieDatabase.getInstance(getApplicationContext());
                        UserDao userDao = movieDatabase.userDao();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                User user = userDao.login(userIdText, passwordText);
                                if (user == null) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    LoggedInUser.getInstance().setUser(user);
                                    LoggedInUser.getInstance().saveUserIdToPreferences(getApplicationContext());
                                    String name = user.getUserId();
                                    startActivity(new Intent(LoginActivity.this, Dashboard.class)
                                            .putExtra("name", name));
                                }
                            }
                        }).start();
                    }
                }
            }
        });

        regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
