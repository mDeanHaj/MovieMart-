package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.moviemart.db.UserDao;
import com.example.moviemart.db.UserDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText userId, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUserId(userId.getText().toString());
                user.setPassword(password.getText().toString());

                if(validateInput(user)){
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.mUserDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Username Registered!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                }else{
                    Toast.makeText(getApplicationContext(), "Fill Username and Password!", Toast.LENGTH_SHORT).show();
                }
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