package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserDao {

    User ifUser(String username, String password);
    User setUser(String username, String password,String email,Integer role);
//    List<Book> getCart( Integer u_id);
}
