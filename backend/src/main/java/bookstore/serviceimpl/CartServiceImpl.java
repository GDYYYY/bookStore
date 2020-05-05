package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.service.BookService;
import com.reins.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public Cart findCartById(Integer u_id, Integer b_id){
        return cartDao.findOne(u_id,b_id);
    }

    @Override
    public Double getCost(Integer u_id, Integer b_id){
        return cartDao.getCost(u_id,b_id);
    }

    @Override
    public List<Cart> getWants(Integer u_id) {
        return cartDao.getWants(u_id);
    }

    @Override
    public Cart updateOne(Integer u_id, Integer b_id,Integer num){
     return cartDao.updateOne(u_id, b_id,num);
    }

    @Override
    public Cart addOne(Integer u_id, Integer b_id,Integer num){
        return cartDao.addOne(u_id, b_id,num);
    }

    @Override
    public Cart delOne(Integer id){
        return cartDao.delOne(id);
    }
}
