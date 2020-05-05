package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.reins.bookstore.entity.User;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public User ifUser(@RequestBody User user){
        return userService.ifUser(user.username, user.password);
    }

    @RequestMapping("/signup")
    public User setUser(@RequestBody User user){
        return userService.setUser(user.username, user.password,user.email,user.role);
    }

//    @RequestMapping("/getCart")
//    public List<Book> getCart(@RequestParam("u_id") Integer u_id){
//        return userService.getCart(u_id);
//    }
}
