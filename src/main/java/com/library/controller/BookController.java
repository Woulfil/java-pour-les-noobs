package com.library.controller;

import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateBookException;
import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listBooks(@RequestParam(defaultValue = "title") String sort) {
        return switch (sort.toLowerCase()) {
            case "author" -> bookService.getBooksSortedByAuthor();
            case "isbn" -> bookService.getBooksSortedByIsbn();
            default -> bookService.getBooksSortedByTitle();
        };
    }

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DuplicateBookException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        boolean removed = bookService.deleteBookByIsbn(isbn);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
