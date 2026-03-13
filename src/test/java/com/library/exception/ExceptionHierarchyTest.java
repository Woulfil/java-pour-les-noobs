package com.library.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la hiérarchie d'exceptions
 */
public class ExceptionHierarchyTest {

    @Test
    void shouldThrowLibraryException() {
        assertThrows(LibraryException.class, () -> {
            throw new LibraryException("Test exception");
        });
    }

    @Test
    void shouldThrowBookNotFoundExceptionAsLibraryException() {
        assertThrows(LibraryException.class, () -> {
            throw new BookNotFoundException("Book not found");
        });
    }

    @Test
    void shouldThrowDuplicateBookExceptionAsLibraryException() {
        assertThrows(LibraryException.class, () -> {
            throw new DuplicateBookException("Duplicate ISBN");
        });
    }

    @Test
    void shouldHaveCorrectExceptionMessage() {
        String message = "Test message";
        BookNotFoundException exception = new BookNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldSupportCause() {
        Throwable cause = new RuntimeException("Cause");
        LibraryException exception = new LibraryException("Message", cause);

        assertEquals(cause, exception.getCause());
    }
}
