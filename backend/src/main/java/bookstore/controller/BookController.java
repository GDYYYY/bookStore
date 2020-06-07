package com.reins.bookstore.controller;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @RequestMapping("/getTargets")
    public List<Book> getTargets(@RequestParam("target") String target) {
        return bookService.getTargets(target);
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("b_id") Integer b_id){
        return bookService.findBookById(b_id);
    }

    @RequestMapping("/addBook")
    public Book setBook(){
        return bookService.setBook();
    }

    @RequestMapping("/updateBook")
    public Book updateBook(@RequestBody Book b){
        return bookService.updateBook(b);
    }

    @RequestMapping("/delBook")
    public Book delBook(@RequestParam("b_id") Integer b_id){
        return bookService.delBook(b_id);
    }

    @RequestMapping("/getAdmTargets")
    public List<Book> getAdmTargets(@RequestParam("target") String target) {
        return bookService.getAdmTargets(target);
    }
}
