package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.OrdersDao;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.Orders;
import com.reins.bookstore.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public Orders findOrdersById(Integer u_id, Integer b_id){
        return ordersDao.findOne(u_id,b_id);
    }

    @Override
    public List<Orders> getBelongs(Integer u_id) {
        return ordersDao.getBelongs(u_id);
    }
    @Override
    public Orders addOne(Integer u_id, Integer b_id, Integer num){
        return ordersDao.addOne(u_id, b_id,num);
    }
}
