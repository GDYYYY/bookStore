package com.reins.bookstore.service;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Cart;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface CartService {

    Cart findCartById(Integer u_id, Integer b_id);
    Double getCost(Integer u_id, Integer b_id);
    Cart updateOne(Integer u_id, Integer b_id,Integer num);
    Cart addOne(Integer u_id, Integer b_id,Integer num);
    Cart delOne(Integer id);
    List<Cart> getWants(Integer u_id);
}
