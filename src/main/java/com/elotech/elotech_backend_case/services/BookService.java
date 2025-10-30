package com.elotech.elotech_backend_case.services;

import com.elotech.elotech_backend_case.dtos.BookCreateDto;
import com.elotech.elotech_backend_case.models.BookModel;
import com.elotech.elotech_backend_case.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }

    public Optional<BookModel> findById(Long id) {
        return bookRepository.findById(id);
    }

    public BookModel save(@Valid BookCreateDto bookCreateDto) {
        BookModel bookModel = new BookModel();
        bookModel.setAutor(bookCreateDto.getAutor());
        bookModel.setTitulo(bookCreateDto.getTitulo());
        bookModel.setCategoria(bookCreateDto.getCategoria());
        bookModel.setIsbn(bookCreateDto.getIsbn());
        bookModel.setData_publicacao(bookCreateDto.getData_publicacao());

        return bookRepository.save(bookModel);
    }

    public BookModel update(Long id, @Valid BookModel newBookModel) {
        BookModel existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setAutor(newBookModel.getAutor());
        existingBook.setTitulo(newBookModel.getTitulo());
        existingBook.setCategoria(newBookModel.getCategoria());
        existingBook.setIsbn(newBookModel.getIsbn());
        existingBook.setData_publicacao(newBookModel.getData_publicacao());

        return bookRepository.save(existingBook);
    }

    public void delete(BookModel bookModel) {
        bookRepository.deleteById(bookModel.getId());
    }
}
