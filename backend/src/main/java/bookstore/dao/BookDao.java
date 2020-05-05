package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(Integer b_id);

    List<Book> getBooks();
    Book setBook();
    Book updateBook(Book b);
    Book delBook(Integer b_id);
    List<Book> getTargets(String tar);
    List<Book> getAdmTargets(String tar);
}
