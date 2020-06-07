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
        Book book = new Book();
        book.setName("bookname");
        book.setAuthor("author");
//        book.setDescription("null");
        book.setPrice(999.9);
        book.setStock(0);
        book.setOnshelf(0);
//        book.setIsbn("null");
        System.out.println(111);
        return bookDao.saveBook(book);
    }

    @Override
    public Book updateBook(Book b){
        return bookDao.saveBook(b);
    }

    @Override
    public Book delBook(Integer b_id) {
        Book cur = new Book();
        cur.setB_id(b_id);
        return bookDao.delBook(cur);
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
