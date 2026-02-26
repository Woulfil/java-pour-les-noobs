public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Clean Code", "Robert Martin", 2008, "978-0132350884"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", 2018, "978-0134685991"));

        library.displayBooks();
    }
}