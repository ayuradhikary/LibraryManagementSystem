package LibraryManagementSystem;

import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class Main implements Utilities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookManager manager = new BookManager();
        try {
            manager.loadBooks();
        } catch (ExceptionHandler ex) {
            System.out.println(ex.getMessage());
        }

        String currentUser = "";

        System.out.println("username: ");
        String userName = sc.next();
        System.out.println("password: ");
        String Currentpassword = sc.next();

        while (true) {

            try {
                currentUser = UserManager.validateUser(userName, Currentpassword);
            } catch (Exception ex) {
                System.out.println(ex);
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
                        if (Utilities.continueOrBreak().equals("logout")) {
                            return;
                        } else {
                            break;
                        }
                    case 2:
                        Utilities.addBook(currentUser, manager);
                        if (Utilities.continueOrBreak().equals("logout")) {
                            return;
                        } else {
                            break;
                        }

                    case 3:
                        Utilities.deleteBook(currentUser, manager);
                        if (Utilities.continueOrBreak().equals("logout")) {
                            return;
                        } else {
                            break;
                        }

                    case 4:
                        Utilities.displayBooks(manager);
                        if (Utilities.continueOrBreak().equals("logout")) {
                            return;
                        } else {
                            break;
                        }
                    default:
                        System.out.println("wrong input");
                        if (Utilities.continueOrBreak().equals("logout")) {
                            return;
                        } else {
                            break;
                        }
                }
            } else if (currentUser.equals("user")) {
                System.out.println("As a user you can:- ");
                System.out.println("1. Display books (press 1) 2. Borrow Books (press 2)");
                int userInput = sc.nextInt();
                switch (userInput) {
                    case 1:
                        Utilities.displayBooks(manager);
                        if (Utilities.continueOrBreak().equals("logout")) {
                            return;
                        } else {
                            break;
                        }
                    case 2:
                        Utilities.borrowBooks(currentUser, manager);
                        if (Utilities.continueOrBreak().equals("logout")) {
                            return;
                        } else {
                            break;
                        }
                    default:
                        System.out.println("wrong input");
                        break;
                }
            } else {
                System.out.println(currentUser);
                break;
            }
        }
    }

}
