package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.UserDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reins.bookstore.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User ifUser(String username, String password){
        return userDao.ifUser(username,password);
    }

    @Override
    public User setUser(String username, String password,String email,Integer role){
        return userDao.setUser(username, password,email, role);
    }

//    @Override
//    public List<Book> getCart(@RequestBody Integer u_id){
//        return userDao.getCart(u_id);
//    }
}
