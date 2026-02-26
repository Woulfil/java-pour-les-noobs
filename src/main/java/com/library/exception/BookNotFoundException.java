package com.library.exception;

/**
 * Exception levée quand un livre n'est pas trouvé
 */
public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
