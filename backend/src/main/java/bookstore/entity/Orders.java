package com.reins.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Orders {

    @Id
    @Column(name = "id")
    private int id;
    public int b_id;
    public int u_id;
    public int f_id;
    public int num;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public int getB_id(){return b_id;}
    public void setB_id(Integer b_id){this.b_id=b_id;}
    public int getU_id(){return u_id;}
    public void setU_id(Integer u_id){this.u_id=u_id;}
    public int getF_id(){return f_id;}
    public void setF_id(Integer f_id){this.f_id=f_id;}
    public int getNum(){return num;}
    public void setNum(Integer num){this.num=num;}

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
