package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.dao.OrdersDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Orders;
import com.reins.bookstore.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Orders findOrdersById(Integer f_id, Integer b_id){
        return ordersDao.findOne(f_id,b_id);
    }

    @Override
    public List<Orders> getBelongs(Integer f_id) {
        return ordersDao.getBelongs(f_id);
    }
    @Override
    public List<Orders> getAll() {
        return ordersDao.getAll();
    }

    @Override
    public Orders addOne(Integer f_id,Integer u_id, Integer b_id, Integer num){
        Orders cur;
        cur = new Orders();
        cur.setB_id(b_id);
        cur.setF_id(f_id);
        cur.setU_id(u_id);
        cur.setNum(num);
        Book book1 = bookDao.findOne(b_id);
        book1.setStock(book1.getStock()-num);
        bookDao.saveBook(book1);
        return ordersDao.addOne(cur);
    }
}
