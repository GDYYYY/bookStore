package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Cart findCartById(Integer u_id, Integer b_id){
        return cartDao.findOne(u_id,b_id);
    }

    @Override
    public Double getCost(Integer u_id, Integer b_id){
        Book b=bookDao.findOne(b_id);
        Cart c=cartDao.findOne(u_id,b_id);
        Double ans=b.getPrice()*c.getNum();
        return ans;
    }

    @Override
    public List<Cart> getWants(Integer u_id) {
        return cartDao.getWants(u_id);
    }

    @Override
    public Cart updateOne(Integer u_id, Integer b_id,Integer num){

        Cart cur = cartDao.findOne(u_id,b_id);
        cur.setNum(num);
        return cartDao.saveOne(cur);
    }

    @Override
    public Cart addOne(Integer u_id, Integer b_id,Integer num){
        Cart cur;
        if(cartDao.findOne(u_id,b_id)!=null){
            cur=cartDao.findOne(u_id,b_id);
            cur.setNum(cur.getNum()+num);
        }
        else
        {cur = new Cart();
            cur.setB_id(b_id);
            cur.setU_id(u_id);
            cur.setNum(num);
            }
        return cartDao.saveOne(cur);
    }

    @Override
    public Cart delOne(Integer id){
        Cart cur = new Cart();
        cur.setId(id);
        return cartDao.delOne(cur);
    }
}
