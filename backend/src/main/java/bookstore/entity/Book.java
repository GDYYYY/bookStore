package com.reins.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "b_id")
public class Book {

    @Id
    @Column(name = "b_id")
    private int b_id;

    public String isbn;
    public String name;
    public String author;
    public Double price;
    public String description;
    public int stock;
    public String image;

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
    public void setDescription(String description){this.description=description;}
    public void setIsbn(String isbn){this.isbn=isbn;}
    public void setImage(String image){this.image=image;}
    public void setStock(Integer stock){this.stock=stock;}

//    private Set<User> wanter=new HashSet<>();
//    private Set<User> buyer=new HashSet<>();
//
//    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
//    @JoinTable(
//            name="cart",
//            joinColumns = @JoinColumn(name="b_id",referencedColumnName = "b_id"),
//            inverseJoinColumns = @JoinColumn(name="u_id",referencedColumnName = "u_id")
//
//    )
//    public Set<User> getWanter(){
//        return wanter;
//    }
//    public void setWanter(Set<User> wanter){
//        this.wanter = wanter;
//    }

//    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
//    @JoinTable(
//            name="orders",
//            joinColumns = @JoinColumn(name="b_id",referencedColumnName = "b_id"),
//            inverseJoinColumns = @JoinColumn(name="u_id",referencedColumnName = "u_id")
//
//    )
//    public Set<User> getBuyer(){
//        return buyer;
//    }
//    public void setBuyer(Set<User> buyer){
//        this.buyer = buyer;
//    }
}
