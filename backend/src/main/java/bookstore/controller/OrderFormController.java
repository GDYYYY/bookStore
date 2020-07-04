package com.reins.bookstore.controller;

import com.reins.bookstore.entity.OrderForm;
import com.reins.bookstore.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

@RestController
public class OrderFormController {

    @Autowired
    private OrderFormService orderFormService;

    @RequestMapping("/getAllOrderForm")
    public List<OrderForm> getForms(@RequestParam("u_id") Integer u_id) {
        return orderFormService.getForms(u_id);
    }

    @RequestMapping("/getByTime")
    public List<OrderForm> getByTime(@RequestParam("t1") Timestamp t1,@RequestParam("t2") Timestamp t2) throws ParseException {
        t1.setTime(t1.getTime()+8*60*60*1000);
        t2.setTime(t2.getTime()+8*60*60*1000);
        return orderFormService.getByTime(t1,t2);
    }

    @RequestMapping("/getAllForms")
    public List<OrderForm> getAllForms() {
        return orderFormService.getAllForms();
    }

    @RequestMapping("/newForm")
    public OrderForm newOne(@RequestParam("u_id") Integer u_id,@RequestParam("cost") Double cost){
        System.out.println(u_id);
        return orderFormService.newOne(u_id,cost);
    }
}
