package LibraryManagementSystem;

import java.util.Scanner;

public interface Utilities {

    static void createUser(String currentUser, String Currentpassword, String newUser, String newUserPassword,
            boolean overwrite) {
        try {
            UserManager.createUser(currentUser, Currentpassword, newUser, newUserPassword, false);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }

    }

    static void addBook(String currentUser, BookManager manager) {
        Scanner ob = new Scanner(System.in);
        System.out.println("please enter the name of the book you would like to add: ");
        String book = ob.nextLine();
        try {
            manager.addBook(book, currentUser);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void deleteBook(String currentUser, BookManager manager) {
        try {
            manager.deleteBook(currentUser);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void displayBooks(BookManager manager) {
        try {
            manager.displayBooks();
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void borrowBooks(String currentUser, BookManager manager) {
        try {
            manager.borrowBooks(currentUser);
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }
    }

    static String continueOrBreak() {
        Scanner sc = new Scanner(System.in);
        System.out.println("would you like to continue? :");
        System.out.println("If no than 'logout' to exit else press any key: ");
        String decision = sc.next();
        if (decision.equals("logout")) {
            return "logout";
        } else {
            return "continue";
        }
    }

}
