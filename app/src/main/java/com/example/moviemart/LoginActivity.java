package com.example.moviemart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviemart.db.UserDao;
import com.example.moviemart.db.UserDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText userId, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if(userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all Fields!", Toast.LENGTH_SHORT).show();
                }else{
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.mUserDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.login(userIdText, passwordText);
                            if(user == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                //here
                            }
                        }
                    }).start();
                }
            }
        });
    }
}