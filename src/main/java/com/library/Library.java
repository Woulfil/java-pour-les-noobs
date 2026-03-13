package com.library;

import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateBookException;
import com.library.model.Book;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe représentant une bibliothèque
 */
public class Library {
    // Utiliser un Set pour garantir l'unicité des livres (basée sur equals/hashCode)
    private Set<Book> books = new HashSet<>();
    
    // Map pour recherche rapide par ISBN
    private Map<String, Book> booksByIsbn = new HashMap<>();

    /**
     * Ajouter un livre à la bibliothèque
     * @param book Le livre à ajouter
     * @throws DuplicateBookException si un livre avec le même ISBN existe déjà
     * @throws IllegalArgumentException si le livre est null
     */
    public void addBook(Book book) throws DuplicateBookException {
        if (book == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être null");
        }
        if (booksByIsbn.containsKey(book.getIsbn())) {
            throw new DuplicateBookException("Un livre avec l'ISBN " + book.getIsbn() + " existe déjà");
        }
        books.add(book);
        booksByIsbn.put(book.getIsbn(), book);
    }

    /**
     * Supprimer un livre de la bibliothèque
     * @param book Le livre à supprimer
     * @return true si le livre a été supprimé, false sinon
     */
    public boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }
        boolean removed = books.remove(book);
        if (removed) {
            booksByIsbn.remove(book.getIsbn());
        }
        return removed;
    }

    /**
     * Supprimer un livre par son ISBN
     * @param isbn L'ISBN du livre à supprimer
     * @return true si le livre a été supprimé, false sinon
     */
    public boolean removeBookByIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }
        Book book = booksByIsbn.remove(isbn);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }

    /**
     * Afficher tous les livres de la bibliothèque
     */
    public void displayBooks() {
        books.forEach(System.out::println);
    }

    /**
     * Rechercher un livre par son titre
     * @param title Le titre du livre à rechercher
     * @return Le livre trouvé
     * @throws BookNotFoundException si aucun livre n'est trouvé
     */
    public Book findBookByTitle(String title) throws BookNotFoundException {
        if (title == null) {
            throw new BookNotFoundException("Le titre ne peut pas être null");
        }
        return books.stream()
                .filter(book -> title.equals(book.getTitle()))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Aucun livre trouvé avec le titre: " + title));
    }

    /**
     * Rechercher tous les livres par auteur
     * @param author L'auteur à rechercher
     * @return Une liste de livres trouvés
     */
    public List<Book> findBooksByAuthor(String author) {
        if (author == null) {
            return Collections.emptyList();
        }
        return books.stream()
                .filter(book -> author.equals(book.getAuthor()))
                .collect(Collectors.toList());
    }

    /**
     * Rechercher un livre par son ISBN
     * @param isbn L'ISBN du livre à rechercher
     * @return Le livre trouvé
     * @throws BookNotFoundException si aucun livre n'est trouvé
     */
    public Book findBookByIsbn(String isbn) throws BookNotFoundException {
        if (isbn == null) {
            throw new BookNotFoundException("L'ISBN ne peut pas être null");
        }
        Book book = booksByIsbn.get(isbn);
        if (book == null) {
            throw new BookNotFoundException("Aucun livre trouvé avec l'ISBN: " + isbn);
        }
        return book;
    }

    /**
     * Obtenir le nombre de livres dans la bibliothèque
     * @return Le nombre de livres
     */
    public int getBookCount() {
        return books.size();
    }

    /**
     * Obtenir tous les livres sous forme de liste
     * @return Une liste non modifiable de tous les livres
     */
    public List<Book> getAllBooks() {
        return List.copyOf(books);
    }

    /**
     * Retourne la liste des livres triés par titre (ordre alphabétique).
     * Utilise un Stream pour produire la liste et la méthode sort() pour trier.
     *
     * @return Liste de livres triés par titre
     */
    public List<Book> getBooksSortedByTitle() {
        List<Book> sorted = books.stream().collect(Collectors.toList());
        sorted.sort(Book.TITLE_COMPARATOR);
        return List.copyOf(sorted);
    }

    /**
     * Retourne la liste des livres triés par auteur (ordre alphabétique).
     * Utilise un Stream pour produire la liste et la méthode sort() pour trier.
     *
     * @return Liste de livres triés par auteur
     */
    public List<Book> getBooksSortedByAuthor() {
        List<Book> sorted = books.stream().collect(Collectors.toList());
        sorted.sort(Book.AUTHOR_COMPARATOR);
        return List.copyOf(sorted);
    }
}
