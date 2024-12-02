package LibraryManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<String> books; // = new ArrayList<>();
    // private String currentUser;
    private static final String BOOKS_FILE = "books.txt";
    File file = new File(BOOKS_FILE);

    public BookManager() {
        this.books = new ArrayList<>();
    }

    public void loadBooks() throws ExceptionHandler {
        books.clear();
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
        System.out.println("Add book is triggered!");
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
            System.out.println("Before foor loop" + books);
            for (String book : books) {
                // writer.write(books.get(books.size() - 1));
                System.out.println("before writing: " + books);
                writer.write(book);

                writer.newLine();
                System.out.println("After writing : " + books);
            }
        } catch (Exception ex) {
            throw ExceptionHandler.fileWriteException("Invalid request");
        }
    }

    public void deleteBook(String currentUser) throws ExceptionHandler {
        if (currentUser.equals("admin")) {
            System.out.println("Delete book is triggered");
            System.out.println("List of available books before deletion:");
            System.out.println(books);
            displayBooks(); // Show books before deletion
            System.out.println("Please enter the name of the book you want to delete: ");
            Scanner sc = new Scanner(System.in);
            String book = sc.next();

            if (books.contains(book)) {
                System.out.println("Book found, deleting: " + book);
                books.remove(book);
                System.out.println("List of available books after deletion:");
                displayBooks(); // Show books after deletion
                saveBooks(); // Save updated list to the file
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Could not find the book to delete.");
            }
        } else {
            System.out.println("You must be an admin to delete a book.");
        }
    }

    public void borrowBooks(String currentUser) throws ExceptionHandler {
        if (currentUser.equals("admin")) {
            System.out.println("admin cannot borrow a book");
        } else {
            loadBooks();
            System.out.println("Here are the Books that you can borrow: ");
            displayBooks();
            System.out.println("please mention the name of the book that you want to borrow: ");
            Scanner ob = new Scanner(System.in);
            String book = ob.nextLine();
            if (books.contains(book)) {
                System.out.println("You have successfully borowed the book " + book);
            } else {
                System.out.println("The book " + book + " does not exists in the list of our books");
            }
        }
    }
}
