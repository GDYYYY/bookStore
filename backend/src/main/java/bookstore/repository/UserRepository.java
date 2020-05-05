package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "from User where username = :username and password = :password")
    User ifUser(@Param("username") String username, @Param("password") String password);

}
