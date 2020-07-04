package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.BookInfo;
import com.reins.bookstore.repository.BookInfoRepository;
import com.reins.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Override
    public Book findOne(Integer b_id) {
        return addImage(bookRepository.getOne(b_id));
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = bookRepository.getBooks();
        for (Book book : books) {
            addImage(book);
        }
        return books;
    }

    @Override
    public List<Book> getTargets(String tar) {
        List<Book> books = bookRepository.getTargets(tar);
        for (Book book : books) {
            addImage(book);
        }
        return books;
    }

    @Override
    public List<Book> getAdmTargets(String tar) {
        List<Book> books = bookRepository.getAdmTargets(tar);
        for (Book book : books) {
            addImage(book);
        }
        return books;
    }

    @Override
    public Book saveBook(Book book) {
        Integer id = book.getB_id();
        BookInfo img;
        System.out.println(2222);
        if (book.getBookInfo() != null) {
            Optional<BookInfo> image = bookInfoRepository.findById(id);
            if (image.isPresent()) {
                image.get().setImage(book.getBookInfo().getImage());
//            System.out.println(book.getBookInfo().getImage());
                img = image.get();
//            System.out.println(image.get().getImage());
            } else
                img = new BookInfo(id, book.getBookInfo().getImage());
            System.out.println(3333);
            bookInfoRepository.save(img);
        }
        System.out.println(444);
        return bookRepository.save(book);
    }

    @Override
    public Book addImage(Book book) {
        Optional<BookInfo> Image = bookInfoRepository.findById(book.getB_id());
        if (Image.isPresent()) {
            book.setBookInfo(Image.get());
//            System.out.println(Image.get().getImage());
        } else
            book.setBookInfo(null);
        return book;
    }
    @Override
    public BookInfo delImage(BookInfo book) {
        bookInfoRepository.delete(book);
        return book;
    }

    @Override
    public Book delBook(Book cur) {
        Optional<BookInfo> Image = bookInfoRepository.findById(cur.getB_id());
        if (Image.isPresent()) {
           delImage(Image.get());
        }
        bookRepository.delete(cur);
        return cur;
    }
}
