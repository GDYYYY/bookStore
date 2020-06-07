package com.reins.bookstore.dao;


import com.reins.bookstore.entity.OrderForm;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface OrderFormDao {
//    OrderForm findOne(Integer u_id, Integer b_id);
    OrderForm newOne(OrderForm cur);
    List<OrderForm> getForms(Integer u_id);
    List<OrderForm> getAllForms();
    List<OrderForm> getByTime(Timestamp t1,Timestamp t2);
}
