package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText userId, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUserId((userId.getText().toString()));
                user.setPassword(password.getText().toString());
            }
        });
    }

    private Boolean validateInput(User user){
        if(user.getUserId().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}