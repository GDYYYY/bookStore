package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.OrdersDao;
import com.reins.bookstore.entity.Orders;
import com.reins.bookstore.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders findOne(Integer u_id,Integer b_id){
        return ordersRepository.getOne(u_id,b_id);
    }


    @Override
    public List<Orders> getBelongs(Integer u_id) {
        return ordersRepository.getBelongs(u_id);
    }

    @Override
    public Orders addOne(@Param("u_id") Integer u_id, @Param("b_id") Integer b_id, @Param("num") Integer num){
        Orders cur;
        if(ordersRepository.getOne(u_id,b_id)!=null){
            cur=ordersRepository.getOne(u_id,b_id);
            cur.setNum(cur.getNum()+num);
        }
        else
        {cur = new Orders();
            cur.setB_id(b_id);
            cur.setU_id(u_id);
            cur.setNum(num);}
        Orders cur1=ordersRepository.save(cur);
        return cur1;
    }
}
