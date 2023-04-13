package com.example.moviemart.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviemart.Order;
import com.example.moviemart.User;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insert(Order order);

    @Query("SELECT * FROM order_table WHERE userId = :userId")
    List<Order> getOrdersByUserId(int userId);
}
