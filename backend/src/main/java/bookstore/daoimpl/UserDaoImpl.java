package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.UserDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.reins.bookstore.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public User ifUser(String username, String password) {

        return userRepository.ifUser(username, password);
    }

    @Override
    public User setUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("role") Integer role) {
        User user = new User();
        user.setRole(role);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        User user1 = userRepository.save(user);
        return user1;
    }

//    @Override
//    public List<Book> getCart(@RequestBody Integer u_id){
//        return userRepository.getCart(u_id);
//    }
}
