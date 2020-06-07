package com.reins.bookstore.dao;


import com.reins.bookstore.entity.Cart;

import java.util.List;

public interface CartDao {
    Cart findOne(Integer u_id,Integer b_id);
    Cart saveOne(Cart cur);
    Cart delOne(Cart cur);
    List<Cart> getWants(Integer u_id);
}
