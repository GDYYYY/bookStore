package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/getCart")
    public List<Cart> getWants(@RequestParam("u_id") Integer u_id) {
        return cartService.getWants(u_id);
    }

    @RequestMapping("/getNum")
    public Cart getOne(@RequestParam("u_id") Integer u_id,@RequestParam("b_id") Integer b_id){
        return cartService.findCartById(u_id,b_id);
    }
    @RequestMapping("/updateCart")
    public Cart updateNum(@RequestParam("u_id") Integer u_id,@RequestParam("b_id") Integer b_id,@RequestParam("num") Integer num){
        return cartService.updateOne(u_id, b_id,num);
    }
    @RequestMapping("/addCart")
    public Cart addOne(@RequestParam("u_id") Integer u_id,@RequestParam("b_id") Integer b_id,@RequestParam("num") Integer num){
        return cartService.addOne(u_id, b_id,num);
    }

    @RequestMapping("/delCart")
    public Cart delOne(@RequestParam("id") Integer id){
        return cartService.delOne(id);
    }

    @RequestMapping("/getPrice")
    public Double getCost(@RequestParam("u_id") Integer u_id,@RequestParam("b_id") Integer b_id){
        return cartService.getCost(u_id,b_id);
    }
}
