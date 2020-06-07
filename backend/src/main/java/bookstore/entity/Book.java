package com.reins.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "b_id")
public class Book {

    private int b_id;

    public String isbn;
    public String name;
    public String author;
    public Double price;
    public String description;
    public int stock;
    public int onshelf;


    public BookInfo bookInfo;
    @Transient
    public BookInfo getBookInfo(){
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

//    private BookDetail description;
//    @Transient
//    public BookDetail getDescription(){
//        return description;
//    }
//
//    public void setDescription(BookDetail description) {
//        this.description = description;
//    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    public int getB_id(){
        return b_id;
    }
    public void setB_id(int b_id){
        this.b_id=b_id;
    }

    public Double getPrice(){return this.price;}
    public void setPrice(Double price){this.price=price;}

    public void setAuthor(String author){this.author=author;}
    public void setName(String name){this.name=name;}
    public void setOnshelf(Integer onshelf){this.onshelf=onshelf;}
    public void setDescription(String description){this.description=description;}
    public void setIsbn(String isbn){this.isbn=isbn;}
    public void setStock(Integer stock){this.stock=stock;}
    public int getStock(){return this.stock;}


}
