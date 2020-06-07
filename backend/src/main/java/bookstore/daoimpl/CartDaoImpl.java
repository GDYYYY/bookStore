package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findOne(Integer u_id,Integer b_id){
        return cartRepository.getOne(u_id,b_id);
    }

    @Override
    public List<Cart> getWants(Integer u_id) {
        return cartRepository.getWants(u_id);
    }

    @Override
    public Cart saveOne( Cart cur){

        Cart cur1=cartRepository.save(cur);
        return cur1;
    }

    @Override
    public Cart delOne(Cart cur){
        cartRepository.delete(cur);
        return cur;
    }

}
