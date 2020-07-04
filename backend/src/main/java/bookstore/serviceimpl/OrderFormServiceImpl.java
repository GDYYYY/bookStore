package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.OrderFormDao;
import com.reins.bookstore.entity.OrderForm;
import com.reins.bookstore.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class OrderFormServiceImpl implements OrderFormService {

    @Autowired
    private OrderFormDao orderFormDao;

//    @Override
//    public OrderForm findOrderFormById(Integer f_id, Integer b_id){
//        return orderFormDao.findOne(f_id,b_id);
//    }
//
    @Override
    public List<OrderForm> getForms(Integer u_id) {
        return orderFormDao.getForms(u_id);
    }
    @Override
    public List<OrderForm> getByTime(Timestamp t1,Timestamp t2) {
        System.out.println(t1);
        System.out.println(t2);
        return orderFormDao.getByTime(t1,t2);
    }
    @Override
    public List<OrderForm> getAllForms() {
        return orderFormDao.getAllForms();
    }
    @Override
    public OrderForm newOne(Integer u_id,Double cost){
        OrderForm cur = new OrderForm();
        cur.setU_id(u_id);
        cur.setCost(cost);
        return orderFormDao.newOne(cur);
    }
}
