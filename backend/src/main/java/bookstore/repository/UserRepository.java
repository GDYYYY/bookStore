package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {
    @Query("select b from User b")
    List<User> getUsers();

    @Query(value = "from User where username = :username and password = :password")
    User ifUser(@Param("username") String username, @Param("password") String password);

    @Query(value = "from User where username = :username")
    User existUser(String username);

    @Query(value = "from User where u_id = :u_id")
    User getUser(Integer u_id);
}
