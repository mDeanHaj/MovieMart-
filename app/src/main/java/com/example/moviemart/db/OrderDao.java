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
    void insertOrder(Order order);

    @Query("SELECT * FROM order_table WHERE userName = :userName")
    List<Order> getOrdersByUserName(String userName);
}
