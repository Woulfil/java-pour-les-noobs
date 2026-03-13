package com.library.exception;

/**
 * Exception de base pour les opérations de bibliothèque
 */
public class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }

    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}
