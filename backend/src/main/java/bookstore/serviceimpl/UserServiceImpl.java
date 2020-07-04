package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.UserDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reins.bookstore.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User ifUser(String username, String password){
        User user= userDao.ifUser(username,password);
        return user;
    }
    @Override
    public User getUser(Integer u_id){return userDao.getUser(u_id);}
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
    @Override
    public User setUser(User cur){
        User user=existUser(cur.username);
        if(user==null){
            user=new User();
            user.setRole(cur.role);
            user.setEmail(cur.email);
            user.setPassword(cur.password);
            user.setUsername(cur.username);
            return userDao.setUser(user);
        }
        else return null;
    }
    @Override
    public User updateUser(User user){
        User user1=userDao.getOne(user.username);
            user1.setRole(user.role);
//            user1.setEmail(user.email);
//            user1.setPassword(user.password);
            return userDao.setUser(user1);
    }
    @Override
    public User existUser(String username){
            return userDao.existUser(username);
    }
//    @Override
//    public List<Book> getCart(@RequestBody Integer u_id){
//        return userDao.getCart(u_id);
//    }
}
