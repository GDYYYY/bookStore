package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("select b from Book b")
    List<Book> getBooks();

    @Query("select b from Book b where b.name like CONCAT('%',?1,'%') or b.author like CONCAT('%',?1,'%') ") //or b.description like CONCAT('%',?1,'%')
    List<Book> getTargets(@Param("target") String tar);

    @Query("select b from Book b where b.name like CONCAT('%',?1,'%') or b.author like CONCAT('%',?1,'%') or b.isbn like CONCAT('%',?1,'%')")
    List<Book> getAdmTargets(@Param("target") String tar);
}
