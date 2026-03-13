package com.library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Comparator;

/**
 * Classe représentant un livre
 */
public class Book {
    private String title;
    private String author;
    private int year;
    private final String isbn;

    /**
     * Constructeur d'un livre
     * @param title Titre du livre
     * @param author Auteur du livre
     * @param year Année de publication
     * @param isbn ISBN du livre (immuable)
     */
    @JsonCreator
    public Book(
            @JsonProperty("title") String title,
            @JsonProperty("author") String author,
            @JsonProperty("year") int year,
            @JsonProperty("isbn") String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    /**
     * Comparateurs utilitaires pour trier les livres.
     */
    public static final Comparator<Book> TITLE_COMPARATOR = new TitleComparator();
    public static final Comparator<Book> AUTHOR_COMPARATOR = new AuthorComparator();

    private static class TitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1 == o2) return 0;
            if (o1 == null) return -1;
            if (o2 == null) return 1;
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    private static class AuthorComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1 == o2) return 0;
            if (o1 == null) return -1;
            if (o2 == null) return 1;
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    }
}
