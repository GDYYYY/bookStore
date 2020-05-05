package com.reins.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class User {

    private int u_id;

    public int role;
    public String username;
    public String email;
    public String password;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    public int getU_id(){
        return u_id;
    }
    public void setU_id(int u_id){
        this.u_id=u_id;
    }

    public void setRole(Integer role){
        this.role=role;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }

//    public List<Book> wants=new ArrayList<>();
//    private List<Book> belongs=new ArrayList<>();
//
//    @ManyToMany(mappedBy = "wanter",fetch = FetchType.EAGER)
//    public List<Book> getWants(){
//        return wants;
//    }
//    public void setWants(List<Book> wants){
//        this.wants=wants;
//    }

//    @ManyToMany(mappedBy = "buyer",fetch = FetchType.EAGER)
//    public List<Book> getBelongs(){
//        return belongs;
//    }
//    public void setBelongs(List<Book> belongs){
//        this.belongs=belongs;
//    }
}
