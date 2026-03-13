package com.library;

import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateBookException;
import com.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Library
 */
public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");
        book2 = new Book("Effective Java", "Joshua Bloch", 2018, "978-0134685991");
        book3 = new Book("Java Concurrency", "Joshua Bloch", 2006, "978-0321349606");
    }

    // ========== Tests de recherche par titre ==========

    @Test
    void shouldFindBook() throws DuplicateBookException, BookNotFoundException {
        library.addBook(book1);

        Book found = library.findBookByTitle("Clean Code");

        assertEquals("Clean Code", found.getTitle());
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenTitleNotFound() throws DuplicateBookException {
        library.addBook(book1);

        assertThrows(BookNotFoundException.class, () -> library.findBookByTitle("Inexistent Book"));
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenTitleIsNull() {
        assertThrows(BookNotFoundException.class, () -> library.findBookByTitle(null));
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenLibraryIsEmpty() {
        assertThrows(BookNotFoundException.class, () -> library.findBookByTitle("Clean Code"));
    }

    // ========== Tests de recherche par ISBN ==========

    @Test
    void shouldFindBookByIsbn() throws DuplicateBookException, BookNotFoundException {
        library.addBook(book1);

        Book found = library.findBookByIsbn("978-0132350884");

        assertEquals("Clean Code", found.getTitle());
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenIsbnNotFound() throws DuplicateBookException {
        library.addBook(book1);

        assertThrows(BookNotFoundException.class, () -> library.findBookByIsbn("999-9999999999"));
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenIsbnIsNull() {
        assertThrows(BookNotFoundException.class, () -> library.findBookByIsbn(null));
    }

    // ========== Tests de recherche par auteur ==========

    @Test
    void shouldFindBooksByAuthor() throws DuplicateBookException {
        library.addBook(book2);
        library.addBook(book3);

        List<Book> found = library.findBooksByAuthor("Joshua Bloch");

        assertEquals(2, found.size());
    }

    @Test
    void shouldReturnBooksSortedByTitle() throws DuplicateBookException {
        // Ajout dans un ordre non trié
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book1);

        List<Book> sorted = library.getBooksSortedByTitle();

        assertEquals("Clean Code", sorted.get(0).getTitle());
        assertEquals("Effective Java", sorted.get(1).getTitle());
        assertEquals("Java Concurrency", sorted.get(2).getTitle());
    }

    @Test
    void shouldReturnBooksSortedByAuthor() throws DuplicateBookException {
        // Ajout dans un ordre non trié
        library.addBook(book1);
        library.addBook(book3);
        library.addBook(book2);

        List<Book> sorted = library.getBooksSortedByAuthor();

        assertEquals("Joshua Bloch", sorted.get(0).getAuthor());
        assertEquals("Joshua Bloch", sorted.get(1).getAuthor());
        assertEquals("Robert Martin", sorted.get(2).getAuthor());
    }

    @Test
    void shouldReturnEmptyListWhenAuthorNotFound() throws DuplicateBookException {
        library.addBook(book1);

        List<Book> found = library.findBooksByAuthor("Unknown Author");

        assertTrue(found.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenAuthorIsNull() {
        List<Book> found = library.findBooksByAuthor(null);

        assertTrue(found.isEmpty());
    }

    // ========== Tests d'ajout de livre ==========

    @Test
    void shouldAddBook() throws DuplicateBookException {
        library.addBook(book1);

        assertEquals(1, library.getBookCount());
    }

    @Test
    void shouldThrowDuplicateBookExceptionWhenAddingBookWithSameIsbn() throws DuplicateBookException {
        library.addBook(book1);
        Book duplicate = new Book("Different Title", "Different Author", 2020, "978-0132350884");

        assertThrows(DuplicateBookException.class, () -> library.addBook(duplicate));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenAddingNullBook() {
        assertThrows(IllegalArgumentException.class, () -> library.addBook(null));
    }

    // ========== Tests paramétrés ==========

    @ParameterizedTest
    @CsvSource({
            "Clean Code, Robert Martin, 2008, 978-0132350884",
            "Effective Java, Joshua Bloch, 2018, 978-0134685991",
            "Java Concurrency, Joshua Bloch, 2006, 978-0321349606"
    })
    void shouldAddMultipleBooksSuccessfully(String title, String author, int year, String isbn)
            throws DuplicateBookException {
        Book book = new Book(title, author, year, isbn);

        library.addBook(book);

        assertEquals(1, library.getBookCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {"978-0132350884", "978-0134685991", "978-0321349606"})
    void shouldThrowBookNotFoundForInvalidIsbn(String isbn) {
        assertThrows(BookNotFoundException.class, () -> library.findBookByIsbn(isbn));
    }

    // ========== Tests de suppression ==========

    @Test
    void shouldRemoveBook() throws DuplicateBookException {
        library.addBook(book1);

        boolean removed = library.removeBook(book1);

        assertTrue(removed);
        assertEquals(0, library.getBookCount());
    }

    @Test
    void shouldReturnFalseWhenRemovingNonExistentBook() throws DuplicateBookException {
        library.addBook(book1);

        boolean removed = library.removeBook(book2);

        assertFalse(removed);
    }

    @Test
    void shouldRemoveBookByIsbn() throws DuplicateBookException {
        library.addBook(book1);

        boolean removed = library.removeBookByIsbn("978-0132350884");

        assertTrue(removed);
        assertEquals(0, library.getBookCount());
    }

    @Test
    void shouldReturnFalseWhenRemovingByInvalidIsbn() throws DuplicateBookException {
        library.addBook(book1);

        boolean removed = library.removeBookByIsbn("999-9999999999");

        assertFalse(removed);
    }

    @Test
    void shouldReturnFalseWhenRemovingByNullIsbn() {
        assertFalse(library.removeBookByIsbn(null));
    }

    @Test
    void shouldReturnFalseWhenRemovingNullBook() {
        assertFalse(library.removeBook(null));
    }

    // ========== Tests cas limites ==========

    @Test
    void shouldHandleMultipleBooksCorrectly() throws DuplicateBookException {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        assertEquals(3, library.getBookCount());
    }

    @Test
    void shouldMaintainUnicityOfIsbn() throws DuplicateBookException {
        library.addBook(book1);

        assertThrows(DuplicateBookException.class, () -> {
            library.addBook(new Book("Another Title", "Another Author", 2020, "978-0132350884"));
        });
    }

    @Test
    void shouldReturnUnmodifiableList() throws DuplicateBookException {
        library.addBook(book1);

        List<Book> books = library.getAllBooks();

        assertThrows(UnsupportedOperationException.class, () -> books.add(book2));
    }

    @Test
    void shouldCorrelateRemoveByIsbnAndRemoveByObject() throws DuplicateBookException {
        library.addBook(book1);
        library.addBook(book2);

        boolean removedByIsbn = library.removeBookByIsbn("978-0132350884");
        boolean removedByObject = library.removeBook(book2);

        assertTrue(removedByIsbn);
        assertTrue(removedByObject);
        assertEquals(0, library.getBookCount());
    }
}
