package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

    @Query("select c from Orders c where c.u_id =:u_id")
    List<Orders> getBelongs(@Param("u_id") Integer u_id);

    @Query("select c from Orders c where c.u_id = :u_id and c.b_id = :b_id")
    Orders getOne(@Param("u_id") Integer u_id, @Param("b_id") Integer b_id);
}
