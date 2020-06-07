package com.reins.bookstore.service;

import com.reins.bookstore.entity.OrderForm;

import java.sql.Timestamp;
import java.util.List;


public interface OrderFormService {

//    Orders findOrdersById(Integer f_id, Integer b_id);
    OrderForm newOne(Integer u_id,Double cost);
    List<OrderForm> getForms(Integer u_id);
    List<OrderForm> getAllForms();
    List<OrderForm> getByTime(Timestamp t1,Timestamp t2);
}
