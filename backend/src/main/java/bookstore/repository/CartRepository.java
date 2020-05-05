package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> { //1

    @Query("select c from Cart c where c.u_id =:u_id") //2
    List<Cart> getWants(@Param("u_id") Integer u_id); //3

    @Query("select c from Cart c where c.u_id = :u_id and c.b_id = :b_id") //2
    Cart getOne(@Param("u_id") Integer u_id,@Param("b_id") Integer b_id);

//    Cart updateOne(Integer u_id, Integer b_id,Integer num);
}
