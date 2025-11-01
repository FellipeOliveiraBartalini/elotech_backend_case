package com.elotech.elotech_backend_case.services;

import com.elotech.elotech_backend_case.dtos.BookCreateDto;
import com.elotech.elotech_backend_case.dtos.BookUpdateDto;
import com.elotech.elotech_backend_case.interfaces.IBookService;
import com.elotech.elotech_backend_case.models.BookModel;
import com.elotech.elotech_backend_case.models.CategoryModel;
import com.elotech.elotech_backend_case.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
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
        bookModel.setIsbn(bookCreateDto.getIsbn());
        bookModel.setDataPublicacao(bookCreateDto.getDataPublicacao());

        List<CategoryModel> categories = validateAndGetCategories(bookCreateDto.getCategorias());
        bookModel.setCategorias(categories);

        return bookRepository.save(bookModel);
    }

    public BookModel update(Long id, @Valid BookUpdateDto newBookModel) {
        BookModel existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // TODO: find a way to set an optional if the property is present in the newBookModel
        existingBook.setAutor(newBookModel.getAutor());
        existingBook.setTitulo(newBookModel.getTitulo());
        existingBook.setIsbn(newBookModel.getIsbn());
        existingBook.setDataPublicacao(newBookModel.getDataPublicacao());

        List<CategoryModel> existingCategories = updateCategories(existingBook.getCategorias(), newBookModel.getCategorias());
        existingBook.setCategorias(existingCategories);

        return bookRepository.save(existingBook);
    }

    public void delete(BookModel bookModel) {
        bookRepository.deleteById(bookModel.getId());
    }

    private List<CategoryModel> validateAndGetCategories(List<Long> categoryIds) {
        return categoryIds.stream()
            .map(categoryId -> categoryService.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId)))
            .toList();
    }

    private List<CategoryModel> updateCategories(List<CategoryModel> existingCategories, List<Long> newCategoryIds) {
        existingCategories.clear();
        List<CategoryModel> updatedCategories = validateAndGetCategories(newCategoryIds);
        existingCategories.addAll(updatedCategories);
        return existingCategories;
    }

    public List<BookModel> findByCategoryId(Long categoryId) {
        return bookRepository.findByCategorias_Id(categoryId);
    }
}
