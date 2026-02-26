package com.library;

import com.library.exception.DuplicateBookException;
import com.library.model.Book;

/**
 * Classe principale de démonstration
 */
public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        try {
            library.addBook(new Book("Clean Code", "Robert Martin", 2008, "978-0132350884"));
            library.addBook(new Book("Effective Java", "Joshua Bloch", 2018, "978-0134685991"));

            library.displayBooks();
        } catch (DuplicateBookException e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }
}
