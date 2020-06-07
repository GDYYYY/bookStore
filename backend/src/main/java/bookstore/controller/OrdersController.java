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
    public List<Orders> getBelongs(@RequestParam("f_id") Integer f_id) {
        return ordersService.getBelongs(f_id);
    }

    @RequestMapping("/getAllOrders")
    public List<Orders> getAll() {
        return ordersService.getAll();
    }

    @RequestMapping("/getONum")
    public Orders getOne(@RequestParam("f_id") Integer f_id,@RequestParam("b_id") Integer b_id){
        return ordersService.findOrdersById(f_id,b_id);
    }

    @RequestMapping("/addOrders")
    public Orders addOne(@RequestParam("f_id") Integer f_id, @RequestParam("b_id") Integer b_id, @RequestParam("num") Integer num){
        return ordersService.addOne(f_id, b_id,num);
    }
}
