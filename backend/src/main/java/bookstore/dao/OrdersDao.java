package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Orders;

import java.util.List;

public interface OrdersDao {
    Orders findOne(Integer f_id, Integer b_id);
    Orders addOne(Orders cur);
    List<Orders> getBelongs(Integer f_id);
    List<Orders> getAll();
}
