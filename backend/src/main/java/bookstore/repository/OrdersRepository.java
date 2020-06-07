package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

    @Query("select c from Orders c where c.f_id =:f_id")
    List<Orders> getBelongs(@Param("f_id") Integer f_id);

    @Query("select c from Orders c where c.f_id = :f_id and c.b_id = :b_id")
    Orders getOne(@Param("f_id") Integer f_id, @Param("b_id") Integer b_id);
    @Query("select c from Orders c ")
    List<Orders>  getAll();
}
