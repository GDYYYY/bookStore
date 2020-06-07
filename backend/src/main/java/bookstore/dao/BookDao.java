package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.BookInfo;

import java.util.List;

public interface BookDao {
    Book findOne(Integer b_id);

    List<Book> getBooks();
    Book saveBook(Book b);
    Book delBook(Book cur);
    Book addImage(Book cur);
    BookInfo delImage(BookInfo cur);
    List<Book> getTargets(String tar);
    List<Book> getAdmTargets(String tar);
}
