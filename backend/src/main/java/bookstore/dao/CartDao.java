package com.reins.bookstore.dao;


import com.reins.bookstore.entity.Cart;

import java.util.List;

public interface CartDao {
    Cart findOne(Integer u_id,Integer b_id);
    Double getCost(Integer u_id,Integer b_id);
    Cart updateOne(Integer u_id, Integer b_id,Integer num);
    Cart addOne(Integer u_id, Integer b_id,Integer num);
    Cart delOne(Integer id);
    List<Cart> getWants(Integer u_id);
}
