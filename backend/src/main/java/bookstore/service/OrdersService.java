package com.reins.bookstore.service;

import com.reins.bookstore.entity.Orders;

import java.util.List;


public interface OrdersService {

    Orders findOrdersById(Integer f_id, Integer b_id);
    Orders addOne(Integer f_id, Integer b_id, Integer num);
    List<Orders> getBelongs(Integer f_id);
    List<Orders> getAll();
}
