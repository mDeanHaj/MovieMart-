package com.example.moviemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviemart.db.MovieDatabase;

import java.util.List;

public class CancelOrder extends AppCompatActivity {

    private RecyclerView ordersRecyclerView;
    private OrderAdapter orderAdapter;
    private MovieDatabase movieDatabase;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);

        ordersRecyclerView = findViewById(R.id.recyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersRecyclerView.setHasFixedSize(true);

        movieDatabase = MovieDatabase.getInstance(this);

        new Thread(() -> {
            orders = movieDatabase.orderDao().getOrdersByUserName(LoggedInUser.getInstance().getUser().getUserId());
            runOnUiThread(() -> {
                orderAdapter = new OrderAdapter(orders, order -> {
                    cancelOrder(order);
                });
                ordersRecyclerView.setAdapter(orderAdapter);
            });
        }).start();
    }

    private void cancelOrder(Order order) {
        new Thread(() -> {
            movieDatabase.orderDao().deleteOrder(order);
            orders.remove(order);
            runOnUiThread(() -> {
                orderAdapter.notifyDataSetChanged();
            });
        }).start();
    }

    public void onReturnButtonClick(View view) {
        finish();
    }


    public static class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

        private List<Order> orders;
        private OnItemClickListener onItemClickListener;
        private AlertDialog.Builder cancelOrderDialogBuilder;

        public OrderAdapter(List<Order> orders, OnItemClickListener onItemClickListener) {
            this.orders = orders;
            this.onItemClickListener = onItemClickListener;
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
            return new OrderViewHolder(view, onItemClickListener);
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

        public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView movieTitleTextView;
            OnItemClickListener onItemClickListener;

            public OrderViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
                super(itemView);
                movieTitleTextView = itemView.findViewById(R.id.movieTitleTextView);
                this.onItemClickListener = onItemClickListener;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    showCancelOrderDialog(orders.get(position));
                }
            }

            private void showCancelOrderDialog(Order order) {
                if (cancelOrderDialogBuilder == null) {
                    cancelOrderDialogBuilder = new AlertDialog.Builder(itemView.getContext());
                    cancelOrderDialogBuilder.setTitle("Cancel Order");
                    cancelOrderDialogBuilder.setMessage("Do you want to cancel this order?");
                    cancelOrderDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
                        onItemClickListener.onItemClick(order);
                    });
                    cancelOrderDialogBuilder.setNegativeButton("No", null);
                }
                cancelOrderDialogBuilder.show();
            }
        }

        public interface OnItemClickListener {
            void onItemClick(Order order);
        }
    }


}
