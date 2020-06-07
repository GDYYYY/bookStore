package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.OrdersDao;
import com.reins.bookstore.entity.Orders;
import com.reins.bookstore.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders findOne(Integer f_id,Integer b_id){
        return ordersRepository.getOne(f_id,b_id);
    }

    @Override
    public List<Orders> getAll() {
        return ordersRepository.getAll();
    }

    @Override
    public List<Orders> getBelongs(Integer f_id) {
        return ordersRepository.getBelongs(f_id);
    }

    @Override
    public Orders addOne(Orders cur) {

        System.out.println(cur.num);
        Orders cur1 = ordersRepository.save(cur);
        return cur1;
    }
}
