package com.reins.bookstore.repository;

import com.reins.bookstore.entity.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookInfoRepository extends MongoRepository<BookInfo, Integer> {

}