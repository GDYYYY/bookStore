package com.reins.bookstore.repository;

import com.reins.bookstore.entity.OrderForm;
import com.reins.bookstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderFormRepository extends JpaRepository<OrderForm,Integer> {

//    @Query("select f from OrderForm f where f.u_id =:u_id ")
//    List<OrderForm> getOrders(@Param("u_id") Integer u_id);

//    @Query("select c from Orders c where c.u_id = :u_id and c.b_id = :b_id")
//    Orders getOne(@Param("u_id") Integer u_id, @Param("b_id") Integer b_id);
}
