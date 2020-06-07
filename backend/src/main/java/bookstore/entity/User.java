package com.reins.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


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
    
    private UserIcon userIcon;
    @Transient
    public UserIcon getUserIcon(){
        return userIcon;
    }

    public void setUserIcon(UserIcon userIcon) {
        this.userIcon = userIcon;
    }


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


}
