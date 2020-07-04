package com.reins.bookstore.dao;

import com.reins.bookstore.entity.User;
import java.util.List;
public interface UserDao {

    User ifUser(String username, String password);
    User existUser(String username);
    User setUser(User user);
    User saveUser(User user);
    User getUser(Integer u_id);
    List<User> getUsers();
    User addIcon(User user);
    User getOne(String username);

}
