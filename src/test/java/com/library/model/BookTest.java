package com.library.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Book
 */
public class BookTest {

    @Test
    void shouldCreateBookWithAllFields() {
        Book book = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");

        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert Martin", book.getAuthor());
        assertEquals(2008, book.getYear());
        assertEquals("978-0132350884", book.getIsbn());
    }

    @Test
    void shouldHaveImmutableIsbn() {
        Book book = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");
        String isbn = book.getIsbn();

        assertEquals("978-0132350884", isbn);
    }

    @Test
    void shouldBeEqualWhenIsbnIsSame() {
        Book book1 = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");
        Book book2 = new Book("Different Title", "Different Author", 2020, "978-0132350884");

        assertEquals(book1, book2);
    }

    @Test
    void shouldNotBeEqualWhenIsbnIsDifferent() {
        Book book1 = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");
        Book book2 = new Book("Clean Code", "Robert Martin", 2008, "978-0134685991");

        assertNotEquals(book1, book2);
    }

    @Test
    void shouldHaveSameHashCodeWhenIsbnIsSame() {
        Book book1 = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");
        Book book2 = new Book("Different Title", "Different Author", 2020, "978-0132350884");

        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void shouldHaveDifferentHashCodeWhenIsbnIsDifferent() {
        Book book1 = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");
        Book book2 = new Book("Clean Code", "Robert Martin", 2008, "978-0134685991");

        assertNotEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void shouldNotBeEqualToNull() {
        Book book = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");

        assertNotEquals(book, null);
    }

    @Test
    void shouldNotBeEqualToDifferentType() {
        Book book = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");

        assertNotEquals(book, "978-0132350884");
    }

    @Test
    void shouldReturnCorrectToString() {
        Book book = new Book("Clean Code", "Robert Martin", 2008, "978-0132350884");
        String result = book.toString();

        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Robert Martin"));
        assertTrue(result.contains("2008"));
        assertTrue(result.contains("978-0132350884"));
    }

    @ParameterizedTest
    @CsvSource({
            "Clean Code, Robert Martin, 2008, 978-0132350884",
            "Effective Java, Joshua Bloch, 2018, 978-0134685991",
            "Java Concurrency, Joshua Bloch, 2006, 978-0321349606"
    })
    void shouldCreateBooksWithVariousData(String title, String author, int year, String isbn) {
        Book book = new Book(title, author, year, isbn);

        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(year, book.getYear());
        assertEquals(isbn, book.getIsbn());
    }
}
