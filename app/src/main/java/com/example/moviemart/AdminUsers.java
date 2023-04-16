package com.example.moviemart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviemart.db.MovieDatabase;
import com.example.moviemart.db.UserDao;

import java.util.List;

public class AdminUsers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private MovieDatabase movieDatabase;
    private UserDao userDao;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        movieDatabase = MovieDatabase.getInstance(this);
        userDao = movieDatabase.userDao();

        new Thread(() -> {
            users = userDao.getAllUsers();
            runOnUiThread(() -> {
                userAdapter = new UserAdapter(users);
                recyclerView.setAdapter(userAdapter);
            });
        }).start();
    }

    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        private List<User> users;

        public UserAdapter(List<User> users) {
            this.users = users;
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_item, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, int position) {
            User user = users.get(position);
            holder.userIdTextView.setText(user.getUserId());
        }

        @Override
        public int getItemCount() {
            if (users == null) {
                return 0;
            }
            return users.size();
        }

        public class UserViewHolder extends RecyclerView.ViewHolder {

            TextView userIdTextView;

            public UserViewHolder(View itemView) {
                super(itemView);

                userIdTextView = itemView.findViewById(R.id.userIdTextView);
            }
        }
    }
    
    public void onReturnButtonClick(View view) {
        finish();
    }
}


