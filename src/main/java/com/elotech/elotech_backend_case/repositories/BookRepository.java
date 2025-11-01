package com.elotech.elotech_backend_case.repositories;

import com.elotech.elotech_backend_case.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    List<BookModel> findByCategorias_Id(Long categoryId);
}
