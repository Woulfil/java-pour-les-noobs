package com.library.service;

import com.library.Library;
import com.library.exception.DuplicateBookException;
import com.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    private final Library library = new Library();

    public List<Book> getBooksSortedByTitle() {
        return library.getBooksSortedByTitle();
    }

    public List<Book> getBooksSortedByAuthor() {
        return library.getBooksSortedByAuthor();
    }

    public List<Book> getBooksSortedByIsbn() {
        return library.getAllBooks().stream()
                .sorted(Comparator.comparing(Book::getIsbn))
                .toList();
    }

    public void addBook(Book book) throws DuplicateBookException {
        library.addBook(book);
    }

    public boolean deleteBookByIsbn(String isbn) {
        return library.removeBookByIsbn(isbn);
    }
}
