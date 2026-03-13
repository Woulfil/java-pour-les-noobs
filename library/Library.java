import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books = new ArrayList<>();

    // TODO 4 : Ajouter un livre
    public void addBook(Book book) {
        if (book != null && !bookExistsWithIsbn(book.getIsbn())) {
            books.add(book);
        }
    }

    // Vérifier si un livre avec cet ISBN existe déjà
    private boolean bookExistsWithIsbn(String isbn) {
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) {
                return true;
            }
        }
        return false;
    }

    // TODO 4 (suite) : Supprimer un livre
    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public boolean removeBookByIsbn(String isbn) {
        if (isbn == null) {
            return false;
        }
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) {
                return books.remove(book);
            }
        }
        return false;
    }

    // TODO 5 : Afficher tous les livres
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // TODO 6 : Rechercher un livre par titre
    public Book findBookByTitle(String title) {
        if (title == null) {
            return null;
        }
        return books.stream()
                .filter(book -> title.equals(book.getTitle()))
                .findFirst()
                .orElse(null);
    }

    // TODO 6 (suite) : Rechercher un livre par auteur
    public List<Book> findBooksByAuthor(String author) {
        if (author == null) {
            return List.of();
        }
        return books.stream()
                .filter(book -> author.equals(book.getAuthor()))
                .collect(Collectors.toList());
    }

    // TP3 : Retourner une liste triée de livres
    public List<Book> getBooksSortedByTitle() {
        List<Book> sorted = books.stream().collect(Collectors.toList());
        sorted.sort(Book.TITLE_COMPARATOR);
        return sorted;
    }

    public List<Book> getBooksSortedByAuthor() {
        List<Book> sorted = books.stream().collect(Collectors.toList());
        sorted.sort(Book.AUTHOR_COMPARATOR);
        return sorted;
    }

    // TODO 6 (suite) : Rechercher un livre par ISBN
    public Book findBookByIsbn(String isbn) {
        if (isbn == null) {
            return null;
        }
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }
}