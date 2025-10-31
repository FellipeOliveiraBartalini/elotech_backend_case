package com.elotech.elotech_backend_case.interfaces;

import com.elotech.elotech_backend_case.dtos.BookCreateDto;
import com.elotech.elotech_backend_case.dtos.BookUpdateDto;
import com.elotech.elotech_backend_case.models.BookModel;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    public List<BookModel> findAll();
    public Optional<BookModel> findById(Long id);
    public BookModel save(BookCreateDto bookCreateDto);
    public BookModel update(Long id, BookUpdateDto bookUpdateDto);
    public void delete(BookModel bookModel);
}
