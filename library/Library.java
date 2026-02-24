import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books = new ArrayList<>();

    // TODO 4 : Ajouter un livre
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
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
        for (Book book : books) {
            if (title.equals(book.getTitle())) {
                return book;
            }
        }
        return null;
    }
}