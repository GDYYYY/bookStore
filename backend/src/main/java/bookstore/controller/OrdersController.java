package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Orders;
import com.reins.bookstore.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/getOrders")
    public List<Orders> getBelongs(@RequestParam("u_id") Integer u_id) {
        return ordersService.getBelongs(u_id);
    }

    @RequestMapping("/getONum")
    public Orders getOne(@RequestParam("u_id") Integer u_id,@RequestParam("b_id") Integer b_id){
        return ordersService.findOrdersById(u_id,b_id);
    }

    @RequestMapping("/addOrders")
    public Orders addOne(@RequestParam("u_id") Integer u_id, @RequestParam("b_id") Integer b_id, @RequestParam("num") Integer num){
        return ordersService.addOne(u_id, b_id,num);
    }
}
