package com.reins.bookstore.service;

import com.reins.bookstore.entity.Book;

import java.util.List;


public interface BookService {

    Book findBookById(Integer b_id);

    List<Book> getBooks();
    List<Book> getTargets(String tar);
    List<Book> getAdmTargets(String tar);
    Book setBook();
    Book updateBook(Book b);
    Book delBook(Integer b_id);
}
