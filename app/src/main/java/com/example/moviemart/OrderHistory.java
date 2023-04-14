package com.example.moviemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviemart.db.MovieDatabase;

import java.util.List;

public class OrderHistory extends AppCompatActivity {

    private RecyclerView ordersRecyclerView;
    private OrderAdapter orderAdapter;
    private MovieDatabase movieDatabase;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ordersRecyclerView = findViewById(R.id.recyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersRecyclerView.setHasFixedSize(true);

        orderAdapter = new OrderAdapter(orders);
        ordersRecyclerView.setAdapter(orderAdapter);

        movieDatabase = MovieDatabase.getInstance(this);

        // Retrieve the list of orders from the order_table
        new Thread(() -> {
            orders = movieDatabase.orderDao().getOrdersByUserName(LoggedInUser.getInstance().getUser().getUserId());
            runOnUiThread(() -> {
                orderAdapter.setOrders(orders);
            });
        }).start();
    }

    public void onReturnButtonClick(View view) {
        finish();
    }


    public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

        private List<Order> orders;

        public OrderAdapter(List<Order> orders) {
            this.orders = orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_item, parent, false);
            return new OrderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
            Order order = orders.get(position);
            holder.movieTitleTextView.setText(order.getMovieTitle());
        }

        @Override
        public int getItemCount() {
            if (orders == null) {
                return 0;
            }
            return orders.size();
        }

        public class OrderViewHolder extends RecyclerView.ViewHolder {

            TextView movieTitleTextView;

            public OrderViewHolder(@NonNull View itemView) {
                super(itemView);

                movieTitleTextView = itemView.findViewById(R.id.movieTitleTextView);
            }
        }
    }
}
