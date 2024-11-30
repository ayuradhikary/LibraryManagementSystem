package LibraryManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<String> books;
    // private String currentUser;
    private static final String BOOKS_FILE = "books.txt";
    File file = new File(BOOKS_FILE);

    public BookManager() {
        this.books = new ArrayList<>();
    }

    public void loadBooks() throws ExceptionHandler {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(line);
            }
        } catch (Exception ex) {
            throw ExceptionHandler.resourceNotFoundException("The Books could not be loaded!");
        }
    }

    public void displayBooks() throws ExceptionHandler {
        if (books.isEmpty()) {
            System.out.println("No books available");
        } else {
            System.out.println("Books list: ");
            for (String book : books) {
                System.out.println(book);
            }
        }
    }

    public void addBook(String book, String currentUser) throws ExceptionHandler {
        if (currentUser.equals("admin")) {
            books.add(book);
            saveBooks();
            System.out.println("Book added successfully");
        } else {
            System.out.println("You must be an admin to add a book");
        }
    }

    private void saveBooks() throws ExceptionHandler {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String book : books) {
                writer.write(book);
                writer.newLine();
            }
        } catch (Exception ex) {
            throw ExceptionHandler.fileWriteException("Invalid request");
        }
    }

    public void deleteBook(String book, String currentUser) throws ExceptionHandler {
        if (currentUser.equals("admin")) {
            loadBooks();
            books.remove(book);
            if (books.remove(book)) {
                saveBooks();
                System.out.println("Book deleted succesfully.");
            } else {
                System.out.println("Could not delete the book");
            }
        } else {
            System.out.println("You must be an admin to delete a book");
        }
    }

    public void borrowBooks(String currentUser, String book) throws ExceptionHandler {
        if (currentUser.equals("admin")) {
            System.out.println("admin cannot borrow a book");
        } else {
            loadBooks();
            System.out.println("Here are the Books that you can borrow: ");
            displayBooks();
            if (books.contains(book)) {
                System.out.println("You have successfully borowed the book " + book);
            } else {
                System.out.println("The book " + book + " does not exists in the list of our books");
            }
        }
    }
}
