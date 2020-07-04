package com.reins.bookstore.service;

import com.reins.bookstore.entity.User;
import java.util.List;
public interface UserService {

    User ifUser(String username, String password);
    User updateUser(User user);
    User existUser(String username);
    User getUser(Integer u_id);
    List<User> getUsers();
    User setUser(User user);
}
