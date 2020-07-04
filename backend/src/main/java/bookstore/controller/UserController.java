package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.reins.bookstore.entity.User;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    public List<User> getBooks() {
        return userService.getUsers();
    }

    @RequestMapping("/login")
    public User ifUser(@RequestBody User user){
        System.out.println(user.password);
        return userService.ifUser(user.username, user.password);
    }

    @RequestMapping("/signup")
    public User setUser(@RequestBody User user){
        return userService.setUser(user);
    }
    @RequestMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        System.out.println(user.role);
        return userService.updateUser(user);
    }

    @RequestMapping("/getuser")
    public User getUser(@RequestParam("u_id") Integer u_id){
        return userService.getUser(u_id);
    }
}
