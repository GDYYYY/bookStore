package com.reins.bookstore.repository;

import com.reins.bookstore.entity.UserIcon;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserIconRepository extends MongoRepository<UserIcon, Integer> {

}