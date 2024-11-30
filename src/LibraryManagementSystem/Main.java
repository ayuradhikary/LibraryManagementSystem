package LibraryManagementSystem;

import java.util.Scanner;

public class Main implements Utilities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String currentUser = "";

        while (true) {
            System.out.println("please enter 'quit' whenever you want to quit or 'C' to continue:");
            String decision = sc.nextLine();
            if (!decision.equals("quit")) {
                System.out.println("username: ");
                String userName = sc.next();
                System.out.println("password: ");
                String Currentpassword = sc.next();
                try {
                    currentUser = UserManager.validateUser(userName, Currentpassword);
                } catch (ExceptionHandler ex) {
                    System.out.println(ex.getMessage());
                }
                if (currentUser.equals("admin")) {
                    System.out.println("As an admin you can :- ");
                    System.out.println(
                            "1. create users (press 1) 2.add books (press 2) 3.delete books (press 3) 4.display books(press 4)");
                    int userInput = sc.nextInt();
                    switch (userInput) {
                        case 1:
                            System.out.println("please enter the username of the new user: ");
                            String newUser = sc.next();
                            System.out.println("please enter the password for the new user: ");
                            String newUserPassword = sc.next();
                            Utilities.createUser(currentUser, Currentpassword, newUser, newUserPassword, false);
                            decision = sc.nextLine();
                            break;
                        case 2:
                            System.out.print("please enter the name of the book you would like to add: ");
                            String book = sc.next();
                            Utilities.addBook(book, currentUser);
                            decision = sc.nextLine();
                            break;
                        case 3:
                            System.out.print("please enter the name of the book you want to delete:");
                            String deleteBook = sc.next();
                            Utilities.deleteBook(deleteBook, currentUser);
                            decision = sc.nextLine();
                            break;
                        case 4:
                            System.out.println("Here is the list of all Books in the library: ");
                            Utilities.displayBooks();
                            decision = sc.nextLine();
                            break;
                        default:
                            System.out.println("wrong input");
                            break;
                    }
                } else {
                    System.out.println("As a user you can:- ");
                    System.out.println("1. Display books (press 1) 2. Borrow Books (press 2)");
                    int userInput = sc.nextInt();
                    switch (userInput) {
                        case 1:
                            Utilities.displayBooks();
                            decision = sc.nextLine();
                            break;
                        case 2:
                            System.out.println("please enter the name of the book you want to borrow: ");
                            String borrowBook = sc.nextLine();
                            Utilities.borrowBooks(currentUser, borrowBook);
                            decision = sc.nextLine();
                            break;
                        default:
                            System.out.println("wrong input");
                            break;
                    }
                }
            } else {
                break;
            }
        }
    }

}
