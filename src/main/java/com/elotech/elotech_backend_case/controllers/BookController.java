package com.elotech.elotech_backend_case.controllers;

import com.elotech.elotech_backend_case.dtos.BookCreateDto;
import com.elotech.elotech_backend_case.dtos.BookUpdateDto;
import com.elotech.elotech_backend_case.interfaces.IBookService;
import com.elotech.elotech_backend_case.models.BookModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<BookModel> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookModel getBookById(@PathVariable Long id) {
        return bookService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public BookModel createBook(@Valid @RequestBody BookCreateDto bookCreateDto) {
        return bookService.save(bookCreateDto);
    }

    @PutMapping("/{id}")
    public BookModel updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        return bookService.update(id, bookUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        BookModel book = bookService.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookService.delete(book);
    }
}
