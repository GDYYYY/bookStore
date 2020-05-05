package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer b_id){
        return bookDao.findOne(b_id);
    }

    @Override
    public Book setBook(){
        return bookDao.setBook();
    }

    @Override
    public Book updateBook(Book b){
        return bookDao.updateBook(b);
    }

    @Override
    public Book delBook(Integer b_id) {
        return bookDao.delBook(b_id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public List<Book> getTargets(String tar) {
        return bookDao.getTargets(tar);
    }

    @Override
    public List<Book> getAdmTargets(String tar) {
        return bookDao.getAdmTargets(tar);
    }
}
