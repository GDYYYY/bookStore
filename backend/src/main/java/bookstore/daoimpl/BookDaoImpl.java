package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Integer b_id) {
        return bookRepository.getOne(b_id);
    }


    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    @Override
    public List<Book> getTargets(String tar) {
//        System.out.println("ok");
//        tar="'%"+tar+"%'";
        System.out.println(tar);
        return bookRepository.getTargets(tar);
    }

    @Override
    public List<Book> getAdmTargets(String tar) {
        System.out.println(tar);
        return bookRepository.getAdmTargets(tar);
    }

    @Override
    public Book setBook() {
        Book book = new Book();
//        book.setU_id(4);
        book.setName("bookname");
        book.setAuthor("author");
        book.setDescription("null");
        book.setPrice(999.9);
        book.setImage("null");
        book.setStock(0);
        book.setIsbn("null");
        Book book1 = bookRepository.save(book);
        return book1;
    }

    @Override
    public Book updateBook(Book b) {
        Book book = findOne(b.getB_id());
        book.setName(b.name);
        book.setAuthor(b.author);
        book.setDescription(b.description);
        book.setPrice(b.price);
        book.setImage(b.image);
        book.setStock(b.stock);
        book.setIsbn(b.isbn);
        Book book1 = bookRepository.save(book);
        return book1;
    }

    @Override
    public Book delBook(Integer b_id) {
        System.out.println("ok");
        Book cur = new Book();
        System.out.println("ok");
        cur.setB_id(b_id);
        System.out.println("ok");
        bookRepository.delete(cur);//?
        System.out.println("ok");
        return cur;
    }
}
