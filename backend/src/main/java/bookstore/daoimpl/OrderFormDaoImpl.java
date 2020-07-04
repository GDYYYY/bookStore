package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.OrderFormDao;
import com.reins.bookstore.entity.OrderForm;
import com.reins.bookstore.repository.OrderFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class OrderFormDaoImpl implements OrderFormDao {

    @Autowired
    private OrderFormRepository orderFormRepository;

//    @Override
//    public OrderForm findOne(Integer u_id,Integer b_id){
//        return orderFormRepository.getOne(u_id,b_id);
//    }


    @Override
    public List<OrderForm> getForms(Integer u_id) {
        return orderFormRepository.getForms(u_id);
    }
    @Override
    public List<OrderForm> getAllForms() {
        return orderFormRepository.getAllForms();
    }
    @Override
    public List<OrderForm> getByTime(Timestamp t1,Timestamp t2) {
        System.out.println(t1);
        System.out.println(t2);
        return orderFormRepository.getByTime(t1,t2);
    }
    @Override
    public OrderForm newOne(OrderForm cur) {

        OrderForm cur1 = orderFormRepository.save(cur);
        return cur1;
    }
}
