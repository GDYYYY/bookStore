package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.User;
import com.reins.bookstore.repository.BookRepository;
import com.reins.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Cart findOne(Integer u_id,Integer b_id){
        return cartRepository.getOne(u_id,b_id);
    }

    @Override
    public Double getCost(Integer u_id,Integer b_id){
//        System.out.println("ok");
        Book b=bookRepository.getOne(b_id);
//        System.out.println("ok");
        Cart c=cartRepository.getOne(u_id,b_id);
//        System.out.println("ok");
        Double ans=b.getPrice()*c.getNum();
//        System.out.println("ok");
        return ans;
    }

    @Override
    public List<Cart> getWants(Integer u_id) {
        return cartRepository.getWants(u_id);
    }

    @Override
    public Cart updateOne(@Param("u_id") Integer u_id,@Param("b_id") Integer b_id,@Param("num") Integer num){
        Cart cur = cartRepository.getOne(u_id,b_id);
        cur.setNum(num);
        Cart cur1=cartRepository.save(cur);
        return cur1;
    }

    @Override
    public Cart addOne(@Param("u_id") Integer u_id,@Param("b_id") Integer b_id,@Param("num") Integer num){
         Cart cur;
        if(cartRepository.getOne(u_id,b_id)!=null){
            cur=cartRepository.getOne(u_id,b_id);
            cur.setNum(cur.getNum()+num);
        }
        else
        {cur = new Cart();
        cur.setB_id(b_id);
        cur.setU_id(u_id);
        cur.setNum(num);}
        Cart cur1=cartRepository.save(cur);
        return cur1;
    }

    @Override
    public Cart delOne(@Param("id") Integer id){
        Cart cur = new Cart();
        cur.setId(id);
        cartRepository.delete(cur);
        return cur;
    }

//    @Override
//    public User setUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("role") Integer role) {
//        User user = new User();
//        user.setRole(role);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setUsername(username);
//        User user1 = userRepository.save(user);
//        return user1;
//    }
}
