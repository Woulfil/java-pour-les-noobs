package com.library.exception;

/**
 * Exception levée quand on essaie d'ajouter un livre avec un ISBN qui existe déjà
 */
public class DuplicateBookException extends LibraryException {
    public DuplicateBookException(String message) {
        super(message);
    }

    public DuplicateBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
