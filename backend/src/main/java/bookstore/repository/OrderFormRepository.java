package com.reins.bookstore.repository;

import com.reins.bookstore.entity.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface OrderFormRepository extends JpaRepository<OrderForm,Integer> {

    @Query("select f from OrderForm f where f.u_id =:u_id ")
    List<OrderForm> getForms(@Param("u_id") Integer u_id);
    @Query("select f from OrderForm f ")
    List<OrderForm> getAllForms();
    @Query("select f from OrderForm f where f.time<=:t2 and f.time>=:t1")
    List<OrderForm> getByTime(Timestamp t1,Timestamp t2);
//    @Query("select c from Orders c where c.u_id = :u_id and c.b_id = :b_id")
//    Orders getOne(@Param("u_id") Integer u_id, @Param("b_id") Integer b_id);
}
